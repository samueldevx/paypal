package com.paypal.repos;

import com.paypal.api.payments.Currency;

import javax.persistence.Embeddable;


@Embeddable
public class PaypalCurrency
{
    private String value;
    private String currency;


    public PaypalCurrency()
    {
    }


    public PaypalCurrency(Currency currency)
    {
        this.currency = currency.getCurrency();
        this.value = currency.getValue();
    }
    public PaypalCurrency(String currency, String value)
    {
        this.currency = currency;
        this.value = value;
    }


    public String getCurrency()
    {
        return currency;
    }


    public void setCurrency(String currency)
    {
        this.currency = currency;
    }


    public String getValue()
    {
        return value;
    }


    public void setValue(String value)
    {
        this.value = value;
    }
}
