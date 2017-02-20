package com.epam.parsing.utils;

import com.epam.parsing.bean.Dish;
import com.epam.parsing.bean.MenuItem;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DishSaxHandler implements ContentHandler {

    private List<Dish> foodList = new ArrayList<>();
    private Dish dish;
    private StringBuilder text;
    private Map<String, String> descriptionList = new LinkedHashMap<>();
    private String descrAttribute;
    private String fotoAttribute;

    public List<Dish> getFoodList() {
        return foodList;
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

        System.out.println("Begin!!!");


    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End!!!!");
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

      //  System.out.println("startElement -> " + "uri: " + uri + ", localName: " + localName + ", qName: " + qName);
        text = new StringBuilder();

        if (qName.equals("dish")) {

            dish = new Dish();
            String id = atts.getValue("id");
            if (id.charAt(0) == 'h') {
                dish.setItem(MenuItem.HOT_DISH);
            } else if (id.startsWith("c")) {
                dish.setItem(MenuItem.COLD_DISH);
            } else {
                dish.setItem(MenuItem.BREAKFAST);
            }
            dish.setId(Integer.parseInt(atts.getValue("id").charAt(1) + ""));

        }
        if (qName.equals("description")) {
            if (atts.getValue("price").isEmpty()) {
                descrAttribute = " нет цены";
            } else {
                descrAttribute = atts.getValue("price");
            }
        }
        if (qName.equals("foto")) {
            fotoAttribute = atts.getValue("src");
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase());

        switch (tagName) {
            case NAME:
                dish.setName(text.toString());
                break;
            case FOTO:
                dish.setSrc(fotoAttribute);
                break;
            case PORTION:
                dish.setPortion(text.toString());
                break;
            case DESCRIPTION:
                if (text.length() == 0) {
                    descriptionList.put(dish.getName(), descrAttribute);
                } else {
                    descriptionList.put(text.toString(), descrAttribute);
                }
                    break;
                    case DISH:
                        dish.setDescription(descriptionList);
                        foodList.add(dish);
                        dish = null;
                        descriptionList = new LinkedHashMap<>();
                        break;
                }
        }

        @Override
        public void characters ( char[] ch, int start, int length) throws SAXException {
            text.append(ch, start, length);
        }

        @Override
        public void ignorableWhitespace ( char[] ch, int start, int length) throws SAXException {

        }

        @Override
        public void processingInstruction (String target, String data) throws SAXException {

        }

        @Override
        public void skippedEntity (String name) throws SAXException {

        }
    }
