package com.paypal.repos;

import com.paypal.api.payments.PayoutItemDetails;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class PaypalPayoutItemDetails
{
    private String payoutItemId;
    private String transactionId;
    private String transactionStatus;
    @Embedded
    private PaypalCurrency payoutItemFee;
    private String payoutBatchId;
    private String senderBatchId;
    private String timeProcessed;
    @Embedded
    private PaypalPayoutItem payoutItem;


    public PaypalPayoutItemDetails()
    {
    }


    public PaypalPayoutItemDetails(PayoutItemDetails payoutItemDetails)
    {
        this.payoutItemId = payoutItemDetails.getPayoutItemId();
        this.transactionId = payoutItemDetails.getTransactionId();
        this.transactionStatus = payoutItemDetails.getTransactionStatus();
        this.payoutItemFee = new PaypalCurrency(payoutItemDetails.getPayoutItemFee());
        this.payoutBatchId = payoutItemDetails.getPayoutBatchId();
        this.senderBatchId = payoutItemDetails.getSenderBatchId();
        this.payoutItem = new PaypalPayoutItem(payoutItemDetails.getPayoutItem());
        this.timeProcessed = payoutItemDetails.getTimeProcessed();
    }
    public PaypalPayoutItemDetails(
        String payoutItemId, String transactionId, String transactionStatus, PaypalCurrency payoutItemFee, String payoutBatchId, String senderBatchId,
        PaypalPayoutItem payoutItem, String timeProcessed)
    {
        this.payoutItemId = payoutItemId;
        this.transactionId = transactionId;
        this.transactionStatus = transactionStatus;
        this.payoutItemFee = payoutItemFee;
        this.payoutBatchId = payoutBatchId;
        this.senderBatchId = senderBatchId;
        this.payoutItem = payoutItem;
        this.timeProcessed = timeProcessed;
    }


    public String getPayoutItemId()
    {
        return payoutItemId;
    }


    public void setPayoutItemId(String payoutItemId)
    {
        this.payoutItemId = payoutItemId;
    }


    public String getTransactionId()
    {
        return transactionId;
    }


    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }


    public String getTransactionStatus()
    {
        return transactionStatus;
    }


    public void setTransactionStatus(String transactionStatus)
    {
        this.transactionStatus = transactionStatus;
    }


    public PaypalCurrency getPayoutItemFee()
    {
        return payoutItemFee;
    }


    public void setPayoutItemFee(PaypalCurrency payoutItemFee)
    {
        this.payoutItemFee = payoutItemFee;
    }


    public String getPayoutBatchId()
    {
        return payoutBatchId;
    }


    public void setPayoutBatchId(String payoutBatchId)
    {
        this.payoutBatchId = payoutBatchId;
    }


    public String getSenderBatchId()
    {
        return senderBatchId;
    }


    public void setSenderBatchId(String senderBatchId)
    {
        this.senderBatchId = senderBatchId;
    }


    public String getTimeProcessed()
    {
        return timeProcessed;
    }


    public void setTimeProcessed(String timeProcessed)
    {
        this.timeProcessed = timeProcessed;
    }


    public PaypalPayoutItem getPayoutItem()
    {
        return payoutItem;
    }


    public void setPayoutItem(PaypalPayoutItem payoutItem)
    {
        this.payoutItem = payoutItem;
    }
}
