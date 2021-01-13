package com.city.api.cityAPI.models;

import java.util.Objects;

public class City
{
    
    private String ibge_id;
    
    private String uf;

    private String name;

    private String is_capital;

    private String longitude;

    private String latitude;

    private String alternative_names;

    private String microregion;

    private String mesoregion;

    public City(String ibge_id, String uf, String name, String isCapital, String longitude, String latitude, String alternative_names, String microregion, String mesoregion) {
        this.ibge_id = ibge_id;
        this.uf = uf;
        this.name = name;
        this.is_capital = isCapital;
        this.longitude = longitude;
        this.latitude = latitude;
        this.alternative_names = alternative_names;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    /*
        Getters & Setters
         */
    public String getId_ibge()
    {
        return ibge_id;
    }

    public void setId_ibge(String ibge_id)
    {
        this.ibge_id = ibge_id;
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
        return is_capital;
    }

    public void setCapital(String is_capital)
    {
        this.is_capital = is_capital;
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

    public String getAlternativeNames()
    {
        return alternative_names;
    }

    public void setAlternativeNames(String alternative_names)
    {
        this.alternative_names = alternative_names;
    }

    public String getMicroRegion()
    {
        return microregion;
    }

    public void setMicroRegion(String microregion)
    {
        this.microregion = microregion;
    }

    public String getMesoRegion()
    {
        return mesoregion;
    }

    public void setMesoRegion(String mesoregion)
    {
        this.mesoregion = mesoregion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return ibge_id.equals(city.ibge_id) && uf.equals(city.uf) && name.equals(city.name) && Objects.equals(is_capital, city.is_capital) && longitude.equals(city.longitude) && latitude.equals(city.latitude) && microregion.equals(city.microregion) && mesoregion.equals(city.mesoregion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ibge_id, uf, name, is_capital, longitude, latitude, microregion, mesoregion);
    }

    @Override
    public String toString() {
        return "City{" +
                "ibge_id='" + ibge_id + '\'' +
                ", uf='" + uf + '\'' +
                ", name='" + name + '\'' +
                ", isCapital=" + is_capital +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", alternative_names='" + alternative_names + '\'' +
                ", microregion='" + microregion + '\'' +
                ", mesoregion='" + mesoregion + '\'' +
                '}';
    }

}
