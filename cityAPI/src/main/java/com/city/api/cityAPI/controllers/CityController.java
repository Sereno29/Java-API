package com.city.api.cityAPI.controllers;

import com.city.api.cityAPI.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.city.api.cityAPI.services.CityService;

import java.util.HashMap;
import java.util.List;

@RestController
public class CityController
{

    @Autowired
    private CityService cityService;

    @GetMapping("/capitals")
    public List<City> getCapitals()
    {
        return cityService.getCapitalsOrderedByName();
    }

    @GetMapping("/stateWithMostAndLeastCities")
    public HashMap<String, Integer> getStateWithMostAndLeastCities()
    {
        return cityService.getStateWithMostAndLeastCities();
    }

    @GetMapping("/numberOfCitiesByState")
    public HashMap<String, Integer> getNumberOfCitiesNamesByState()
    {
        return cityService.getNumberOfCitiesNamesByState();
    }

    @GetMapping("/cityById")
    public City getCityById(@RequestParam(value = "ibge_id", defaultValue = "") String ibgeId)
    {
        return cityService.getCityById(ibgeId);
    }

    @GetMapping("/citiesByState")
    public List<String> getCitiesNamesByState(@RequestParam(value = "state", defaultValue = "SP") String stateName)
    {
        return cityService.getCityNamesByState(stateName);
    }

    @GetMapping("/totalNumberOfCities")
    public Integer getTotalNumberOfCities()
    {
        return cityService.getTotalNumberOfCities();
    }
}
