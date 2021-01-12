package com.city.api.cityAPI.services;

import com.city.api.cityAPI.dao.CityDAO;
import com.city.api.cityAPI.beans.CityBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    DSLContext context;

    public List<CityBean> insertCitiesFromCsv(String filename)
    {
        List<CityBean> data = null;
        try {
            data = new CsvToBeanBuilder(new FileReader(filename))
                    .withType(CityBean.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        data.remove(0);
        new CityDAO().insertCitiesFromCsv(data, context);
        return data;
    }
}
