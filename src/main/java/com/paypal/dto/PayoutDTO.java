package com.paypal.dto;

import java.math.BigDecimal;

public class PayoutDTO
{
    private BigDecimal amount;


    public BigDecimal getAmount()
    {
        return amount;
    }


    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
}
