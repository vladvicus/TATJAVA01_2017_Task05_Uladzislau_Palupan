package com.epam.parsing.controller.command.impl;

import com.epam.parsing.bean.Dish;
import com.epam.parsing.controller.command.Command;
import com.epam.parsing.service.DishService;
import com.epam.parsing.service.exception.ServiceException;
import com.epam.parsing.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class ParseBySax implements Command {
    List<Dish> dishes=new ArrayList<>();
    @Override
    public List<?> execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DishService dishService = serviceFactory.getDishService();
        try {
           dishes=dishService.parseBySax();

        } catch (ServiceException e) {
            System.out.println(e);

        }

        return dishes;
    }
}
