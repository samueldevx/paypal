package com.paypal.service;

import com.paypal.dto.DepositDTO;
import com.paypal.base.rest.PayPalRESTException;

public interface DepositService
{
    public void deposit(DepositDTO depositDTO) throws PayPalRESTException;
}
