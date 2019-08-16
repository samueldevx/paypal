package com.paypal.repos;

import com.paypal.api.openidconnect.Address;

import javax.persistence.Embeddable;

@Embeddable
public class PaypalUserAddress
{
    private String streetAddress;
    private String locality;
    private String region;
    private String postalCode;
    private String country;

    public PaypalUserAddress(Address address)
    {
        this.streetAddress = address.getStreetAddress();
        this.locality = address.getLocality();
        this.region = address.getRegion();
        this.postalCode = address.getPostalCode();
        this.country = address.getCountry();
    }


    public PaypalUserAddress(String streetAddress, String locality, String region, String postalCode, String country)
    {
        this.streetAddress = streetAddress;
        this.locality = locality;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }


    public String getStreetAddress()
    {
        return streetAddress;
    }


    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }


    public String getLocality()
    {
        return locality;
    }


    public void setLocality(String locality)
    {
        this.locality = locality;
    }


    public String getRegion()
    {
        return region;
    }


    public void setRegion(String region)
    {
        this.region = region;
    }


    public String getPostalCode()
    {
        return postalCode;
    }


    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }


    public String getCountry()
    {
        return country;
    }


    public void setCountry(String country)
    {
        this.country = country;
    }
}
