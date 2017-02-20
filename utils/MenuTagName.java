package com.epam.parsing.utils;


public enum MenuTagName {

    FOTO,
    PRICE,
    DESCRIPTION,
    NAME,
    COLD_DISHES,
    HOT_DISHES,
    BREAKFASTS,
    HOT_DISH,
    COLD_DISH,
    BREAKFAST,
    ITEMS,
    CALORIES,
    FOOD,
    PORTION,

    DISH;

    public static MenuTagName getElementTagName(String element) {
        switch (element) {
            case "dish":
                return DISH;
            case "price":
                return PRICE;
            case "description":
                return DESCRIPTION;
            case "foto":
                return FOTO;
            case "portion":
                return PORTION;
            case "name":
                return NAME;
            case "items":
                return ITEMS;
            case "cold_dishes":
                return COLD_DISH;
            case "hot_dishes":
                return HOT_DISH;
            case "breakfasts":
                return BREAKFAST;
            default:
                throw new EnumConstantNotPresentException(MenuTagName.class, element);
        }
    }
}


