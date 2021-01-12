package com.city.api.cityAPI.beans;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Objects;

public class CityBean
{
    @CsvBindByPosition(position = 0)
    private String id_ibge;

    @CsvBindByPosition(position = 1)
    private String uf;

    @CsvBindByPosition(position = 6)
    private String name;

    @CsvBindByPosition(position = 3)
    private String isCapital;

    @CsvBindByPosition(position = 4)
    private String longitude;

    @CsvBindByPosition(position = 5)
    private String latitude;

    @CsvBindByPosition(position = 7)
    private String alternative_name;

    @CsvBindByPosition(position = 8)
    private String microRegion;

    @CsvBindByPosition(position = 9)
    private String mesoRegion;

    /*
        Getters & Setters
         */
    public String getId_ibge()
    {
        return id_ibge;
    }

    public void setId_ibge(String id_ibge)
    {
        this.id_ibge = id_ibge;
    }

    public String getUf()
    {
        return uf;
    }

    public void setUf(String uf)
    {
        this.uf = uf;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String isCapital()
    {
        return isCapital;
    }

    public void setCapital(String isCapital)
    {
        this.isCapital = isCapital;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getAlternativeName()
    {
        return alternative_name;
    }

    public void setAlternativeName(String alternative_name)
    {
        this.alternative_name = alternative_name;
    }

    public String getMicroRegion()
    {
        return microRegion;
    }

    public void setMicroRegion(String microRegion)
    {
        this.microRegion = microRegion;
    }

    public String getMesoRegion()
    {
        return mesoRegion;
    }

    public void setMesoRegion(String mesoRegion)
    {
        this.mesoRegion = mesoRegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityBean city = (CityBean) o;
        return id_ibge.equals(city.id_ibge) && uf.equals(city.uf) && name.equals(city.name) && Objects.equals(isCapital, city.isCapital) && longitude.equals(city.longitude) && latitude.equals(city.latitude) && microRegion.equals(city.microRegion) && mesoRegion.equals(city.mesoRegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_ibge, uf, name, isCapital, longitude, latitude, microRegion, mesoRegion);
    }

    @Override
    public String toString() {
        return "City{" +
                "id_ibge='" + id_ibge + '\'' +
                ", uf='" + uf + '\'' +
                ", name='" + name + '\'' +
                ", isCapital=" + isCapital +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", alternative_name='" + alternative_name + '\'' +
                ", microRegion='" + microRegion + '\'' +
                ", mesoRegion='" + mesoRegion + '\'' +
                '}';
    }

}
