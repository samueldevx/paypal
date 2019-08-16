package com.paypal.repos;

import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.FundingOption;
import com.paypal.api.payments.PayerInfo;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class PaypalPayer
{
    private String paymentMethod;
    private String status;
    private String accountType;
    private String accountAge;
    private List<FundingInstrument> fundingInstruments;
    private String fundingOptionId;
    private String externalSelectedFundingInstrumentType;
    private FundingOption relatedFundingOption;
    private PayerInfo payerInfo;
}
