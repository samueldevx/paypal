package com.paypal.service;

import com.paypal.dto.PayoutDTO;
import com.paypal.base.rest.PayPalRESTException;

public interface PayoutService
{
    public void payout(PayoutDTO payoutDTO)throws PayPalRESTException;
}
