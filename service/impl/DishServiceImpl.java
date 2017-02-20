package com.epam.parsing.service.impl;

import com.epam.parsing.bean.Dish;
import com.epam.parsing.dao.DishDao;
import com.epam.parsing.dao.exception.DaoException;
import com.epam.parsing.dao.factory.DaoFactory;
import com.epam.parsing.service.DishService;
import com.epam.parsing.service.exception.ServiceException;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class DishServiceImpl implements DishService {


    private List<Dish> dishes = new ArrayList<>();


    @Override
    public List<Dish> parseBySax() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DishDao bookDao = daoFactory.getBookDao();

            dishes = bookDao.parseBySax();

        } catch (DaoException e) {

            throw new ServiceException(e);
        }
        return dishes;
    }


    @Override
    public List<Dish> parseByStax() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DishDao bookDao = daoFactory.getBookDao();

            dishes = bookDao.parseByStax();

        } catch (DaoException e) {

            throw new ServiceException(e);
        }
        return dishes;
    }

    @Override
    public List<Dish> parseByDom() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DishDao bookDao = daoFactory.getBookDao();

            dishes = bookDao.parseByDom();

        } catch (DaoException e) {

            throw new ServiceException(e);
        }
        return dishes;
    }
}