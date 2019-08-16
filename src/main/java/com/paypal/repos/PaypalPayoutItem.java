package com.paypal.repos;

import com.paypal.api.payments.PayoutItem;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class PaypalPayoutItem
{
    private String recipientType;
    @Embedded
    private PaypalCurrency amount;
    private String note;
    private String receiver;
    private String senderItemId;


    public PaypalPayoutItem()
    {
    }


    public PaypalPayoutItem(PayoutItem payoutItem)
    {
        this.recipientType = payoutItem.getRecipientType();
        this.amount = new PaypalCurrency(payoutItem.getAmount());
        this.note = payoutItem.getNote();
        this.receiver = payoutItem.getReceiver();
        this.senderItemId = payoutItem.getSenderItemId();
    }
    public PaypalPayoutItem(String recipientType, PaypalCurrency amount, String note, String receiver, String senderItemId)
    {
        this.recipientType = recipientType;
        this.amount = amount;
        this.note = note;
        this.receiver = receiver;
        this.senderItemId = senderItemId;
    }


    public String getRecipientType()
    {
        return recipientType;
    }


    public void setRecipientType(String recipientType)
    {
        this.recipientType = recipientType;
    }


    public PaypalCurrency getAmount()
    {
        return amount;
    }


    public void setAmount(PaypalCurrency amount)
    {
        this.amount = amount;
    }


    public String getNote()
    {
        return note;
    }


    public void setNote(String note)
    {
        this.note = note;
    }


    public String getReceiver()
    {
        return receiver;
    }


    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }


    public String getSenderItemId()
    {
        return senderItemId;
    }


    public void setSenderItemId(String senderItemId)
    {
        this.senderItemId = senderItemId;
    }
}
