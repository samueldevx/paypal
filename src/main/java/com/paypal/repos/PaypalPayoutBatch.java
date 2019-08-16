package com.paypal.repos;

import com.paypal.api.payments.PayoutBatch;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class PaypalPayoutBatch
{
    @Embedded
    private PaypalPayoutBatchHeader batchHeader;
    @Embedded
    private List<PaypalPayoutItemDetails> items;


    public PaypalPayoutBatch()
    {
    }


    public PaypalPayoutBatch(PayoutBatch payoutBatch)
    {
        this.batchHeader = new PaypalPayoutBatchHeader(payoutBatch.getBatchHeader());
        if(payoutBatch.getItems()!=null&&payoutBatch.getItems().size()>0)
        this.items = payoutBatch.getItems().stream().map(payoutItemDetails -> new PaypalPayoutItemDetails(payoutItemDetails)).collect(Collectors.toList());
    }
    public PaypalPayoutBatch(PaypalPayoutBatchHeader batchHeader, List<PaypalPayoutItemDetails> items)
    {
        this.batchHeader = batchHeader;
        this.items = items;
    }
}
