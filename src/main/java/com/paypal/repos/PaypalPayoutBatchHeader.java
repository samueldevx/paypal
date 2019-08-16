package com.paypal.repos;

import com.paypal.api.payments.*;

import javax.persistence.*;

@Embeddable
public class PaypalPayoutBatchHeader
{
    private String payoutBatchId;
    private String batchStatus;
    private String timeCreated;
    private String timeCompleted;
    @Embedded
    private PaypalPayoutSenderBatchHeader senderBatchHeader;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="currency",column=@Column(name="amount_currency")),
        @AttributeOverride(name="value",column=@Column(name="amount_value"))
        })
    private PaypalCurrency amount;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="currency",column=@Column(name="fees_currency")),
        @AttributeOverride(name="value",column=@Column(name="fees_value"))
    })
    private PaypalCurrency fees;


    public PaypalPayoutBatchHeader()
    {
    }


    public PaypalPayoutBatchHeader(PayoutBatchHeader payoutBatchHeader)
    {
        this.payoutBatchId = payoutBatchHeader.getPayoutBatchId();
        this.batchStatus = payoutBatchHeader.getBatchStatus();
        this.timeCreated = payoutBatchHeader.getTimeCreated();
        this.timeCompleted = payoutBatchHeader.getTimeCompleted();
        this.senderBatchHeader = new PaypalPayoutSenderBatchHeader(payoutBatchHeader.getSenderBatchHeader());
        if(payoutBatchHeader.getAmount()!= null)
        this.amount = new PaypalCurrency(payoutBatchHeader.getAmount());
        if(payoutBatchHeader.getFees()!= null)
        this.fees = new PaypalCurrency(payoutBatchHeader.getFees());
    }
    public PaypalPayoutBatchHeader(
        String payoutBatchId, String batchStatus, String timeCreated, String timeCompleted, PaypalPayoutSenderBatchHeader senderBatchHeader,
        PaypalCurrency amount, PaypalCurrency fees)
    {
        this.payoutBatchId = payoutBatchId;
        this.batchStatus = batchStatus;
        this.timeCreated = timeCreated;
        this.timeCompleted = timeCompleted;
        this.senderBatchHeader = senderBatchHeader;
        this.amount = amount;
        this.fees = fees;
    }


    public String getPayoutBatchId()
    {
        return payoutBatchId;
    }


    public void setPayoutBatchId(String payoutBatchId)
    {
        this.payoutBatchId = payoutBatchId;
    }


    public String getBatchStatus()
    {
        return batchStatus;
    }


    public void setBatchStatus(String batchStatus)
    {
        this.batchStatus = batchStatus;
    }


    public String getTimeCreated()
    {
        return timeCreated;
    }


    public void setTimeCreated(String timeCreated)
    {
        this.timeCreated = timeCreated;
    }


    public String getTimeCompleted()
    {
        return timeCompleted;
    }


    public void setTimeCompleted(String timeCompleted)
    {
        this.timeCompleted = timeCompleted;
    }


    public PaypalPayoutSenderBatchHeader getSenderBatchHeader()
    {
        return senderBatchHeader;
    }


    public void setSenderBatchHeader(PaypalPayoutSenderBatchHeader senderBatchHeader)
    {
        this.senderBatchHeader = senderBatchHeader;
    }


    public PaypalCurrency getAmount()
    {
        return amount;
    }


    public void setAmount(PaypalCurrency amount)
    {
        this.amount = amount;
    }


    public PaypalCurrency getFees()
    {
        return fees;
    }


    public void setFees(PaypalCurrency fees)
    {
        this.fees = fees;
    }
}
