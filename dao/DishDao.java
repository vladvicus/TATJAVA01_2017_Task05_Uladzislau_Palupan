package com.epam.parsing.dao;

import com.epam.parsing.bean.Dish;
import com.epam.parsing.dao.exception.DaoException;


import java.sql.SQLException;
import java.util.List;


 
public interface DishDao {



    List<Dish> parseByStax() throws DaoException;

    List<Dish> parseByDom() throws DaoException;
    
    List<Dish> parseBySax() throws DaoException;
    
}
