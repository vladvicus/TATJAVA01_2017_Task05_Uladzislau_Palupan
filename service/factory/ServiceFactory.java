package com.epam.parsing.service.factory;

import com.epam.parsing.service.DishService;
import com.epam.parsing.service.impl.DishServiceImpl;


public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final DishService dishService = new DishServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance()

    {
        return instance;
    }

    public DishService getDishService() {
        return dishService;
    }

}
