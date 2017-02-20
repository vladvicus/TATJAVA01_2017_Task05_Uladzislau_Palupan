package com.epam.parsing.dao.impl;

import com.epam.parsing.bean.Dish;
import com.epam.parsing.bean.MenuItem;
import com.epam.parsing.dao.DishDao;
import com.epam.parsing.dao.exception.DaoException;
import com.epam.parsing.utils.DishSaxHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;

public class DishDaoImpl implements DishDao {
    private static final String FOTO = "foto";
    private static final String DESCRIPTION = "description";
    private static final String DATAFILE = Paths.get("data/menu.xml").toAbsolutePath().toString();
    private static final String ID = "id";
    private static final String PRICE = "price";
    private static final String SRC = "src";
    private static final String NAME = "name";
    private static final String DISH = "dish";
    private static final String PORTION = "portion";
    private static final String NO_PRICE = " нет цены";

    private static boolean nameFlag;
    private static boolean descrFlag;
    private static boolean portionFlag;

    @Override
    public List<Dish> parseByStax() throws DaoException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();


        List<Dish> menu = parseXMLfile(DATAFILE);
        for (Dish dish : menu) {
            System.out.println(dish.getItem() + ", " + dish.getId() + ", " + dish.getSrc() + ", " + dish.getName() + ", " + dish.getDescription() + ", " + dish.getPortion());
        }
        return menu;


    }

    private List<Dish> parseXMLfile(String fileName) {
        Map<String, String> descriptionList = new LinkedHashMap<>();
        String descrAttribute = "";
        List<Dish> dishList = new ArrayList<>();
        Dish dish = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(fileName));
            // получаем первое событие
            int event = reader.getEventType();
            // обходим весь XML файл
            while (true) {
                // проходим по типам событий
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:

                        if (reader.getLocalName().equals(DISH)) {
                            dish = new Dish();

                            String id = reader.getAttributeValue(0);
                            if (id.charAt(0) == 'h') {
                                dish.setItem(MenuItem.HOT_DISH);
                            } else if (id.startsWith("c")) {
                                dish.setItem(MenuItem.COLD_DISH);
                            } else {
                                dish.setItem(MenuItem.BREAKFAST);
                            }
                            dish.setId(Integer.parseInt(id.charAt(1) + ""));
                        } else if (reader.getLocalName().equals(NAME)) {
                            nameFlag = true;
                        } else if (reader.getLocalName().equals(DESCRIPTION)) {
                            descrAttribute = reader.getAttributeValue(0);
                            descrFlag = true;
                        } else if (reader.getLocalName().equals(FOTO)) {
                            dish.setSrc(reader.getAttributeValue(0));
                        } else if (reader.getLocalName().equals(PORTION)) {
                            portionFlag = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        if (nameFlag) {
                            dish.setName(reader.getText());
                            nameFlag = false;
                        } else if (descrFlag) {
                            descriptionList.put(reader.getText().trim(), descrAttribute);
                            descrFlag = false;
                        } else if (portionFlag) {
                            dish.setPortion(reader.getText());
                            portionFlag = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if (reader.getLocalName().equals(DISH)) {
                            dish.setDescription(descriptionList);
                            dishList.add(dish);
                            descriptionList = new LinkedHashMap<>();
                        }
                        break;
                }
                // если больше элементов нет, то заканчиваем обход файла
                if (!reader.hasNext())
                    break;

                // переход к следующему событию
                event = reader.next();
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return dishList;
    }

    @Override
    public List<Dish> parseByDom() throws DaoException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document document = null;
        try {
            db = dbf.newDocumentBuilder();
            document = db.parse(DATAFILE);
        } catch (ParserConfigurationException e) {
            throw new DaoException(e);
        } catch (SAXException e) {
            throw new DaoException(e);
        } catch (IOException e) {
            throw new DaoException(e);
        }

        List<Dish> menu = new ArrayList<>();
        Element root = document.getDocumentElement();
        NodeList dishNodes = root.getElementsByTagName(DISH);
        Dish dish = null;
        for (int i = 0; i < dishNodes.getLength(); i++) {
            dish = new Dish();
            Element foodElement = (Element) dishNodes.item(i);
            String id = foodElement.getAttribute(ID);
            if (id.charAt(0) == 'h') {
                dish.setItem(MenuItem.HOT_DISH);
            } else if (id.startsWith("c")) {
                dish.setItem(MenuItem.COLD_DISH);
            } else {
                dish.setItem(MenuItem.BREAKFAST);
            }
            dish.setId(Integer.parseInt(foodElement.getAttribute(ID).charAt(1) + ""));

            dish.setSrc(getSingleChild(foodElement, FOTO).getAttribute(SRC));
            dish.setName(getSingleChild(foodElement, NAME).getTextContent().trim());

            NodeList descList = foodElement.getElementsByTagName(DESCRIPTION);
            Map<String, String> descriptionList = new HashMap<>();
            for (int k = 0; k < descList.getLength(); k++) {
                Element desc = (Element) descList.item(k);
                if (desc.getAttribute(PRICE).isEmpty()) {
                    descriptionList.put(descList.item(k).getTextContent(),NO_PRICE);
                } else {
                    descriptionList.put(descList.item(k).getTextContent(), desc.getAttribute(PRICE));
                }
            }

            dish.setDescription(descriptionList);

            dish.setPortion(getSingleChild(foodElement, PORTION).getTextContent());


            menu.add(dish);
        }

        return menu;
    }

    private Element getSingleChild(Element element, String childName) {

        NodeList nlist = element.getElementsByTagName(childName);

        Element child = (Element) nlist.item(0);
        return child;
    }

    @Override
    public List<Dish> parseBySax() throws DaoException {

        DishSaxHandler handler = null;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            handler = new DishSaxHandler();

            reader.setContentHandler(handler);

            reader.parse(new InputSource(DATAFILE));
        } catch (SAXException e) {
            throw new DaoException(e);
        } catch (IOException e) {
            throw new DaoException(e);
        }

        List<Dish> menu = handler.getFoodList();

        return menu;
    }
}
