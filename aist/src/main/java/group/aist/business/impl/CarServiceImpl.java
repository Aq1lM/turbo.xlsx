package group.aist.business.impl;

import group.aist.business.CarService;
import group.aist.entities.Brand;
import group.aist.entities.Car;
import group.aist.entities.Model;
import group.aist.inMemory.CarRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class containing business logic for car
 *
 * @author AqilM
 */

public class CarServiceImpl implements CarService {
    private final String baseSitePath = "https://turbo.az/autos";
    private String pagePath = "";

    private final CarRepository carRepository;


    public CarServiceImpl() {
        this.carRepository = new CarRepository();
    }

    /**
     * add car Details CarRepository from site
     */

    @Override
    public void addCarDetailFromSite() {

        int page = 0;

        while (page <= 5) {

            Document doc;
            try {
                if (page != 0)
                    pagePath = pagePath.substring(6);
                doc = Jsoup.connect(baseSitePath + pagePath).get();

                page = Integer.parseInt(doc.select("span.page.current").text());

                List<Element> elementList = getElements(doc);
                List<Document> innerDocs = getInnerDoc(elementList);

                save(innerDocs);

                pagePath = doc.select("a[rel=next]").first().attr("href");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * get elements from document
     *
     * @param doc - Document obj
     * @return List of Element
     */

    private List<Element> getElements(Document doc) {
        return new ArrayList<>(doc.select("div.products-i"));
    }

    /**
     * get list of document from inside link
     *
     * @param elements - list of Element
     * @return list of document
     */

    private List<Document> getInnerDoc(List<Element> elements) {
        List<Document> innerDocs = new ArrayList<>();

        elements.forEach(element -> {
            try {
                innerDocs.add(Jsoup.connect(baseSitePath.substring(0, baseSitePath.length() - 5)
                        + element.select("a").attr("href")).get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return innerDocs;
    }

    /**
     * get price of car from Document
     *
     * @param doc - Document obj
     * @return price as BigDecimal
     */

    private BigDecimal getPriceFromSite(Document doc) {
        String priceText = doc.getElementsByClass("product-price__i product-price__i--bold").first().text()
                .replaceAll("[^\\d.]", "").trim();
        return new BigDecimal(priceText);
    }

    /**
     * get currency of car price from Document
     *
     * @param doc - Document obj
     * @return currency of price
     */

    private String getCurrencyFromSite(Document doc) {
        String[] currency = doc.getElementsByClass("product-price__i product-price__i--bold").first().text().split("\\s+");

        return currency[currency.length - 1];
    }

    /**
     * create Car object
     *
     * @param doc - Document obj
     * @return car object
     */

    private Car createCar(Document doc) {
        String currency = getCurrencyFromSite(doc);

        BigDecimal price = getPriceFromSite(doc);

        String city = "";
        String body = "";
        String color = "";
        String engine = "";
        String transmission = "";
        String driveType = "";
        String situation = "";
        String tradeRegion = "";
        String seatCount = "";
        String owners = "";

        Brand brand = new Brand("");
        Model model = new Model(brand, "");

        int productionYear = 0;
        int march = 0;

        boolean isNew = false;

        Car car = new Car(city, color, body, engine, transmission,
                driveType, situation, tradeRegion, currency, seatCount, owners, march, productionYear, isNew, price, model);

        Elements elementsInLabel = doc.getElementsByClass("product-properties__i-name");
        Elements elementsInSpan = doc.getElementsByClass("product-properties__i-value");

        for (int i = 0; i < elementsInLabel.size(); ++i) {
            if (elementsInLabel.get(i).text().equalsIgnoreCase("şəhər")) {
                if (city.isEmpty())
                    city = elementsInSpan.get(i).text();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("marka")) {
                if (brand.getBrand().isEmpty())
                    brand.setBrand(elementsInSpan.get(i).text());
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("model")) {
                if (model.getModel().isEmpty()) {
                    model.setModel(elementsInSpan.get(i).text());
                    model.setBrand(brand);
                }
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("buraxılış ili")) {
                if (productionYear == 0)
                    productionYear = Integer.parseInt(elementsInSpan.get(i).text());
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("ban növü")) {
                if (body.isEmpty())
                    body = elementsInSpan.get(i).text();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("rəng")) {
                if (color.isEmpty())
                    color = elementsInSpan.get(i).text();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("mühərrik")) {
                if (engine.isEmpty())
                    engine = elementsInSpan.get(i).text();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("yürüş")) {
                if (march == 0)
                    march = Integer.parseInt(elementsInSpan.get(i).text().replaceAll("[^\\d.]", ""));
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("sürətlər qutusu")) {
                if (transmission.isEmpty())
                    transmission = elementsInSpan.get(i).text();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("ötürücü")) {
                if (driveType.isEmpty())
                    driveType = elementsInSpan.get(i).text();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("yeni")) {
                isNew = elementsInSpan.get(i).text().equals("Bəli");
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("yerlərin sayı")) {
                if (seatCount.isEmpty())
                    seatCount = elementsInSpan.get(i).text().trim();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("sahiblər")) {
                if (owners.isEmpty())
                    owners = elementsInSpan.get(i).text();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("vəziyyəti")) {
                if (situation.isEmpty())
                    situation = elementsInSpan.get(i).text();
            } else if (elementsInLabel.get(i).text().equalsIgnoreCase("hansı bazar üçün yığılıb")) {
                if (tradeRegion.isEmpty())
                    tradeRegion = elementsInSpan.get(i).text();
            }
        }
        car.setCity(city);
        car.setColor(color);
        car.setBody(body);
        car.setEngine(engine);
        car.setTransmission(transmission);
        car.setDriveType(driveType);
        car.setSituation(situation);
        car.setTradeRegion(tradeRegion);
        car.setSeatCount(seatCount);
        car.setMarch(march);
        car.setProductionYear(productionYear);
        car.setNew(isNew);

        return car;
    }

    /**
     * save car object to inMemoryRepository
     *
     * @param docs - list of Document
     */

    private void save(List<Document> docs) {

        Car car;

        for (Document innerDoc : docs) {

            String key = innerDoc.select("div.product-actions__id").text().split(" ")[2];
            car = createCar(innerDoc);

            this.carRepository.carMap.put(key, car);
        }
    }

    /**
     * @return CarRepository
     */

    @Override
    public CarRepository getAccessRepository() {
        return carRepository;
    }
}

