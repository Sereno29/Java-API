package com.city.api.cityAPI.dao;

import com.city.api.cityAPI.jooq.sample.model.Tables;
import com.city.api.cityAPI.jooq.sample.model.tables.records.CityRecord;
import com.city.api.cityAPI.beans.CityBean;
import com.city.api.cityAPI.models.City;
import org.jooq.*;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.jooq.impl.DSL.*;

@Repository
public class CityDAO
{

    public void insertCitiesFromCsv(List<CityBean> csvCities, DSLContext context)
    {
        context.delete(Tables.CITY_).execute();
        for(CityBean city : csvCities)
        {
            context.insertInto(Tables.CITY_)
                .set(Tables.CITY_.IBGE_ID, Long.parseLong(city.getId_ibge()))
                .set(Tables.CITY_.UF, city.getUf())
                .set(Tables.CITY_.NAME, city.getName())
                .set(Tables.CITY_.IS_CAPITAL, Boolean.parseBoolean(city.isCapital()))
                .set(Tables.CITY_.LONGITUDE, Double.parseDouble(city.getLongitude()))
                .set(Tables.CITY_.LATITUDE, Double.parseDouble(city.getLatitude()))
                .set(Tables.CITY_.ALTERNATIVE_NAMES, city.getAlternativeName())
                .set(Tables.CITY_.MESOREGION, city.getMesoRegion())
                .set(Tables.CITY_.MICROREGION, city.getMicroRegion())
                .execute()
                ;
        }
    }

    public List<City> getCapitalsOrderedByName(DSLContext context)
    {
         Result<CityRecord> r = context
                .selectFrom(Tables.CITY_)
                .where(Tables.CITY_.IS_CAPITAL.eq(true))
                .orderBy(Tables.CITY_.NAME)
                .fetch();
         List<City> capitals = new ArrayList<>();
         for(CityRecord record: r)
         {
             capitals.add(
                     new City(
                             record.getIbgeId().toString(),
                             record.getUf(),
                             record.getName(),
                             record.getIsCapital().toString(),
                             record.getLongitude().toString(),
                             record.getLatitude().toString(),
                             record.getAlternativeNames(),
                             record.getMicroregion(),
                             record.getMesoregion()
                     )
             );
         };
         return capitals;
    }

    public HashMap<String, Integer> getStateWithMostAndLeastCities(DSLContext context)
    {
        Result<Record2<String, Integer>> r =  context
                .select( Tables.CITY_.UF, count(Tables.CITY_.UF) )
                .from(Tables.CITY_)
                .where()
                .groupBy(Tables.CITY_.UF)
                .orderBy(count(Tables.CITY_.UF))
                .fetch();
        HashMap <String, Integer> states = new HashMap<>();
        states.put(r.get(0).component1(), r.get(0).component2());
        states.put(r.get(r.size()-1).component1(), r.get(r.size()-1).component2());
        return states;
    }

    public HashMap<String, Integer> getNumberOfCitiesNamesByState(DSLContext context)
    {
         Result<Record2<String, Integer>> r =  context
                .select(Tables.CITY_.UF, count(Tables.CITY_.UF))
                .from(Tables.CITY_)
                .groupBy(Tables.CITY_.UF)
                .fetch();
         HashMap <String, Integer> states = new HashMap<>();
         for(Record2<String, Integer> state : r)
         {
             states.put(state.component1(), state.component2());
         }
         return states;
    }

    public City getCityById(String ibgeId, DSLContext context)
    {
        Result<CityRecord> r = context
                .selectFrom(Tables.CITY_)
                .where(Tables.CITY_.IBGE_ID.eq(Long.parseLong(ibgeId)))
                .fetch();
        if(r.size()>0){
            return new City(
                    r.get(0).getIbgeId().toString(),
                    r.get(0).getUf(),
                    r.get(0).getName(),
                    r.get(0).getIsCapital().toString(),
                    r.get(0).getLongitude().toString(),
                    r.get(0).getLatitude().toString(),
                    r.get(0).getAlternativeNames(),
                    r.get(0).getMicroregion(),
                    r.get(0).getMesoregion()
            );
        }
        return null;
    }

    public List<String> getCityNamesByState(String state, DSLContext context)
    {
        Result<CityRecord> r = context
                .selectFrom(Tables.CITY_)
                .where(Tables.CITY_.UF.eq(state.toUpperCase(Locale.ROOT)))
                .orderBy(Tables.CITY_.NAME)
                .fetch();
        List<String> cityNames = new ArrayList<>();
        for(CityRecord record: r)
        {
            cityNames.add(record.getName());
        };
        return cityNames;
    }

    public Integer getTotalNumberOfCities(DSLContext context)
    {
        Result<Record1<Integer>> r = context
                .select(count())
                .from(Tables.CITY_)
                .fetch();
        return r.get(0).component1();
    }
}
