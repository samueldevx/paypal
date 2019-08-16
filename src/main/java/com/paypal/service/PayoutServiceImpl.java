package com.paypal.service;

import com.paypal.dto.PayoutDTO;
import com.paypal.repos.PaypalPayoutBatch;
import com.paypal.repos.PaypalPayoutRepo;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PayoutServiceImpl implements PayoutService
{
    private static final Logger LOG = LoggerFactory.getLogger(PayoutServiceImpl.class);
    @Value("${paypal.client.app}")
    private String clientAppID;
    @Value("${paypal.client.secret}")
    private String clientAppSecret;
    @Autowired
    private PaypalPayoutRepo paypalPayoutRepo;


    @Override public void payout(PayoutDTO payoutDTO) throws PayPalRESTException
    {
        try
        {
            PayoutBatch payoutBatch = createSynchronousSinglePayout(payoutDTO);
            PaypalPayoutBatch paypalPayoutBatch = new PaypalPayoutBatch(payoutBatch);
            com.paypal.repos.Payout payout = new com.paypal.repos.Payout();
            payout.setPaypalPayoutBatch(paypalPayoutBatch);
            paypalPayoutRepo.save(payout);
        }
        catch (PayPalRESTException e)
        {
            LOG.error("exception occurred while trying to payout", e);
            throw e;
        }
    }


    public PayoutBatch createSynchronousSinglePayout(PayoutDTO payoutDTO) throws PayPalRESTException
    {
        // ###Payout
        // A resource representing a payout
        Payout payout = new Payout();

        PayoutSenderBatchHeader senderBatchHeader = new PayoutSenderBatchHeader();

        // #### Batch Header Instance
        Random random = new Random();
        senderBatchHeader.setSenderBatchId(
            new Double(random.nextDouble()).toString()).setEmailSubject(
            "You have a Payout!");
        // ### Currency
        Currency amount = new Currency();
        amount.setValue(payoutDTO.getAmount().toEngineeringString()).setCurrency("USD");

        // #### Sender Item
        // Please note that if you are using single payout with sync mode, you
        // can only pass one Item in the request
        PayoutItem senderItem = new PayoutItem();
        senderItem.setRecipientType("Email")
            .setNote("Thanks for your patronage")
            .setReceiver("shirt-supplier-one@gmail.com")
            .setSenderItemId("201404324234").setAmount(amount);

        List<PayoutItem> items = new ArrayList<PayoutItem>();
        items.add(senderItem);

        payout.setSenderBatchHeader(senderBatchHeader).setItems(items);

        PayoutBatch batch = null;

        // ### Api Context
        // Pass in a `ApiContext` object to authenticate
        // the call and to send a unique request id
        // (that ensures idempotency). The SDK generates
        // a request id if you do not pass one explicitly.
        APIContext apiContext = new APIContext(clientAppID, clientAppSecret, "sandbox");
        Map<String, String> parameters = new HashMap();
        parameters.put("sync_mode", "false");
        // ###Create Payout Synchronous
        batch = payout.create(apiContext, parameters);

        LOG.info("Payout Batch With ID: "
            + batch.getBatchHeader().getPayoutBatchId());

        return batch;
    }
}
