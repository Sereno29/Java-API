package com.city.api.cityAPI.services;

import com.city.api.cityAPI.dao.CityDAO;
import com.city.api.cityAPI.models.City;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CityService {

    @Autowired
    DSLContext context;

    public List<City> getCapitalsOrderedByName()
    {
        return new CityDAO().getCapitalsOrderedByName(context);
    }

    public HashMap<String, Integer> getStateWithMostAndLeastCities()
    {
        return new CityDAO().getStateWithMostAndLeastCities(context);
    }

    public HashMap<String, Integer> getNumberOfCitiesNamesByState()
    {
        return new CityDAO().getNumberOfCitiesNamesByState(context);
    }

    public City getCityById(String ibgeId)
    {
        return new CityDAO().getCityById(ibgeId, context);
    }

    public List<String> getCityNamesByState(String stateName)
    {
        return new CityDAO().getCityNamesByState(stateName, context);
    }

    public Integer getTotalNumberOfCities()
    {
        return new CityDAO().getTotalNumberOfCities(context);
    }

}
