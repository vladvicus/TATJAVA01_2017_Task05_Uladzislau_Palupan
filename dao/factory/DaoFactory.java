package com.epam.parsing.dao.factory;

import com.epam.parsing.dao.DishDao;
import com.epam.parsing.dao.impl.DishDaoImpl;


public final class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final DishDao bookDao = new DishDaoImpl();



    private DaoFactory(){}

    public static DaoFactory getInstance()

    {
        return instance;
    }

    public DishDao getBookDao()
    {
        return bookDao;

    }

}
