package com.paypal.repos;

import com.paypal.api.payments.PayoutSenderBatchHeader;

import javax.persistence.Embeddable;


@Embeddable
public class PaypalPayoutSenderBatchHeader
{
    private String senderBatchId;
    private String emailSubject;
    private String recipientType;


    public PaypalPayoutSenderBatchHeader()
    {
    }


    public PaypalPayoutSenderBatchHeader(PayoutSenderBatchHeader payoutSenderBatchHeader)
    {
        this.senderBatchId = payoutSenderBatchHeader.getSenderBatchId();
        this.emailSubject = payoutSenderBatchHeader.getEmailSubject();
        this.recipientType = payoutSenderBatchHeader.getRecipientType();
    }
    public PaypalPayoutSenderBatchHeader(String senderBatchId, String emailSubject, String recipientType)
    {
        this.senderBatchId = senderBatchId;
        this.emailSubject = emailSubject;
        this.recipientType = recipientType;
    }


    public String getSenderBatchId()
    {
        return senderBatchId;
    }


    public void setSenderBatchId(String senderBatchId)
    {
        this.senderBatchId = senderBatchId;
    }


    public String getEmailSubject()
    {
        return emailSubject;
    }


    public void setEmailSubject(String emailSubject)
    {
        this.emailSubject = emailSubject;
    }


    public String getRecipientType()
    {
        return recipientType;
    }


    public void setRecipientType(String recipientType)
    {
        this.recipientType = recipientType;
    }
}
