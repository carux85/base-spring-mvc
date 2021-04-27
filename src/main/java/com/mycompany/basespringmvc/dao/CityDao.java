package com.mycompany.basespringmvc.dao;

import java.util.List;

import com.mycompany.basespringmvc.models.City;

public interface CityDao {
	
   void save(City city);
   City findById(long id);
   List<City> findByName(String name);
   List<City> findByCountry(String country);
   List<City> findAll();
   void delete(City city);
}
