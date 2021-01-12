package com.city.api.cityAPI.controllers;

import com.city.api.cityAPI.beans.CityBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.city.api.cityAPI.services.CsvService;

import java.util.List;

@RestController
public class CsvController {

    @Autowired
    private CsvService csvService;


    @PostMapping("/insertCities")
    public List<CityBean> insertCities(@RequestBody String fileName)
    {
        return csvService.insertCitiesFromCsv(fileName);
    }
}
