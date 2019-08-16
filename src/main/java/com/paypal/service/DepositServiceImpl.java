package com.paypal.service;

import com.paypal.dto.DepositDTO;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
@Service
public class DepositServiceImpl implements DepositService
{
    private static final Logger LOG = LoggerFactory.getLogger(DepositServiceImpl.class);
    @Value("${paypal.client.app}")
    private String clientAppID;
    @Value("${paypal.client.secret}")
    private String clientAppSecret;


    @Override public void deposit(DepositDTO  depositDTO) throws PayPalRESTException
    {
        Payment payment= createDepositPayment(depositDTO);
    }
    public Payment createDepositPayment(DepositDTO depositDTO)throws PayPalRESTException{
        Payment createdPayment = null;
        APIContext apiContext = null;
        apiContext = new APIContext(clientAppID, clientAppSecret, "sandbox");

        Details details = new Details();
        details.setShipping(depositDTO.getShipping().toEngineeringString());
        details.setSubtotal(depositDTO.getSubtotal().toEngineeringString());
        details.setTax(depositDTO.getTax().toEngineeringString());

        Amount amount = new Amount();
        amount.setCurrency("USD");

        amount.setTotal(depositDTO.getShipping().add(depositDTO.getSubtotal()).add(depositDTO.getTax()).toEngineeringString());
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction
            .setDescription("This is the payment transaction description.");

        Item item = new Item();
        item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice("5");
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        itemList.setItems(items);

        transaction.setItemList(itemList);

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        String guid = UUID.randomUUID().toString().replaceAll("-", "");
        redirectUrls.setCancelUrl("/paymentwithpaypal?guid=" + guid);
        redirectUrls.setReturnUrl("/paymentwithpaypal?guid=" + guid);
        payment.setRedirectUrls(redirectUrls);
        try
        {
            createdPayment = payment.create(apiContext);
            LOG.info("Created payment with id = "
                + createdPayment.getId() + " and status = "
                + createdPayment.getState());

            Iterator<Links> links = createdPayment.getLinks().iterator();
            while (links.hasNext())
            {
                Links link = links.next();
                if (link.getRel().equalsIgnoreCase("approval_url"))
                {
                    //                    req.setAttribute("redirectURL", link.getHref());
                }
            }
            LOG.info("Payment with PayPal", Payment.getLastRequest(), Payment.getLastResponse(), null);
        }
        catch (PayPalRESTException e)
        {
            LOG.error("Payment with PayPal", Payment.getLastRequest(), null, e.getMessage());
            throw e;
        }
        return createdPayment;
    }
}
