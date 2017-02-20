package com.epam.parsing.service;

import com.epam.parsing.bean.Dish;
import com.epam.parsing.service.exception.ServiceException;

import java.util.List;


public interface DishService {

    List<Dish> parseBySax() throws ServiceException;
    List<Dish> parseByStax() throws ServiceException;
    List<Dish> parseByDom() throws ServiceException;
}