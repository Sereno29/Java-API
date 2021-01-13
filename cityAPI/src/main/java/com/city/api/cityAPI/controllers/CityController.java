package com.city.api.cityAPI.controllers;

import com.city.api.cityAPI.beans.CityBean;
import com.city.api.cityAPI.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/insertCity")
    public City insertCity(@RequestBody City city)
    {
        return cityService.insertCity(city);
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<Long> deleteCity(@PathVariable Long id)
    {
        if(cityService.deleteCity(id) != null)
        {
            return new ResponseEntity<>(id, HttpStatus.OK);
        };
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/columnFiltered")
    public List<City> getColumnFiltered(@RequestParam(value = "column", defaultValue = "ibge_id") String column, @RequestParam(value = "stringToFilter", defaultValue = "") String stringToFilter)
    {
        return cityService.getColumnFiltered(column, stringToFilter);
    }

    @GetMapping("/registersInColumn")
    public Integer getRegistersInColumn(@RequestParam(value = "column", defaultValue = "ibge_id") String column)
    {
        return cityService.getRegistersInColumn(column);
    }

    @GetMapping("/totalNumberOfCities")
    public Integer getTotalNumberOfCities()
    {
        return cityService.getTotalNumberOfCities();
    }
}
