package com.city.api.cityAPI.models;

import java.util.Objects;

public class City
{
    
    private String id_ibge;
    
    private String uf;

    private String name;

    private String isCapital;

    private String longitude;

    private String latitude;

    private String alternative_name;

    private String microRegion;

    private String mesoRegion;

    public City(String id_ibge, String uf, String name, String isCapital, String longitude, String latitude, String alternative_name, String microRegion, String mesoRegion) {
        this.id_ibge = id_ibge;
        this.uf = uf;
        this.name = name;
        this.isCapital = isCapital;
        this.longitude = longitude;
        this.latitude = latitude;
        this.alternative_name = alternative_name;
        this.microRegion = microRegion;
        this.mesoRegion = mesoRegion;
    }

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
        City city = (City) o;
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
