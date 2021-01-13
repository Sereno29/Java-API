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
                .set(Tables.CITY_.UF, city.getUf().toUpperCase(Locale.ROOT))
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

    public City insertCity(City city, DSLContext context)
    {
        context.insertInto(Tables.CITY_)
                .set(Tables.CITY_.IBGE_ID, Long.parseLong(city.getId_ibge()))
                .set(Tables.CITY_.UF, city.getUf().toUpperCase(Locale.ROOT))
                .set(Tables.CITY_.NAME, city.getName())
                .set(Tables.CITY_.IS_CAPITAL, Boolean.parseBoolean(city.isCapital()))
                .set(Tables.CITY_.LONGITUDE, Double.parseDouble(city.getLongitude()))
                .set(Tables.CITY_.LATITUDE, Double.parseDouble(city.getLatitude()))
                .set(Tables.CITY_.ALTERNATIVE_NAMES, city.getAlternativeNames())
                .set(Tables.CITY_.MESOREGION, city.getMesoRegion())
                .set(Tables.CITY_.MICROREGION, city.getMicroRegion())
                .execute()
        ;
        return city;
    }

    public Long deleteCity(Long ibge_id, DSLContext context)
    {
        context.deleteFrom(Tables.CITY_)
                .where(Tables.CITY_.IBGE_ID.eq(ibge_id))
                .execute()
        ;
        return ibge_id;
    }

    public List<City> getColumnFiltered(String column, String stringToFilter, DSLContext context)
    {
        TableField<?, ?> columnJooq = getColumnFromString(column);
        Result <CityRecord> r = context
                .selectFrom(Tables.CITY_)
                .where(columnJooq.like("%" + stringToFilter + "%"))
                .fetch();
        List<City> filteredCities = new ArrayList<>();
        for(CityRecord record: r)
        {
            filteredCities.add(
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
        return filteredCities;
    }


    public Integer getRegistersInColumn(String column, DSLContext context)
    {
        TableField<?, ?> columnJooq = getColumnFromString(column);
        Result<? extends Record1<?>> r = context
                .selectDistinct(columnJooq)
                .from(Tables.CITY_)
                .fetch();
        return r.size();
    }

    public Integer getTotalNumberOfCities(DSLContext context)
    {
        Result<Record1<Integer>> r = context
                .select(count())
                .from(Tables.CITY_)
                .fetch();
        return r.get(0).component1();
    }


    private TableField<?,?> getColumnFromString(String column)
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ibge_id", 0);
        map.put("uf", 1);
        map.put("name", 2);
        map.put("is_capital", 3);
        map.put("longitude", 4);
        map.put("latitude", 5);
        map.put("alternative_names", 6);
        map.put("microregion", 7);
        map.put("mesoregion", 8);

        if(map.containsKey(column)){
            switch(map.get(column))
            {
                case 0:
                    return Tables.CITY_.IBGE_ID;
                case 1:
                    return Tables.CITY_.UF;
                case 2:
                    return Tables.CITY_.NAME;
                case 3:
                    return Tables.CITY_.IS_CAPITAL;
                case 4:
                    return Tables.CITY_.LONGITUDE;
                case 5:
                    return Tables.CITY_.LATITUDE;
                case 6:
                    return Tables.CITY_.ALTERNATIVE_NAMES;
                case 7:
                    return Tables.CITY_.MICROREGION;
                case 8:
                    return Tables.CITY_.MESOREGION;
                default:
                    return null;
            }
        }
        return null;
    }

    // Query for retrieving the max distance of cities

//    SELECT MAX(city.calculate_distance(CAST(MAXDISTANCE.lat1 as double precision), CAST(MAXDISTANCE.lon1 as double precision), CAST(MAXDISTANCE.lat2 as double precision), CAST(MAXDISTANCE.lon2 as double precision), 'K')) AS distancia, MAXDISTANCE.id_ibge1, MAXDISTANCE.id_ibge2 FROM (
//        SELECT DISTINCT city1.ibge_id as id_ibge1, city1.latitude AS lat1, city1.longitude AS lon1, city2.ibge_id as id_ibge2, city2.latitude AS lat2, city2.longitude AS lon2
//        FROM  city.city as city1
//        CROSS JOIN city.city as city2
//        WHERE (city1.uf  = 'RR' AND city2.uf='RS') OR (city1.uf='AC' AND city2.uf='PB')
//        ) AS MAXDISTANCE
//    GROUP BY MAXDISTANCE.id_ibge1, MAXDISTANCE.id_ibge2
//    ORDER BY distancia DESC
//    LIMIT 1;
}
