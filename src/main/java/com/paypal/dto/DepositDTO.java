package com.paypal.dto;

import java.math.BigDecimal;

public class DepositDTO
{
    private BigDecimal amount;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal shipping;


    public BigDecimal getAmount()
    {
        return amount;
    }


    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }


    public BigDecimal getSubtotal()
    {
        return subtotal;
    }


    public void setSubtotal(BigDecimal subtotal)
    {
        this.subtotal = subtotal;
    }


    public BigDecimal getTax()
    {
        return tax;
    }


    public void setTax(BigDecimal tax)
    {
        this.tax = tax;
    }


    public BigDecimal getShipping()
    {
        return shipping;
    }


    public void setShipping(BigDecimal shipping)
    {
        this.shipping = shipping;
    }
}
