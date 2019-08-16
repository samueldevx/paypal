package com.paypal.controller;

import com.paypal.dto.PayoutDTO;
import com.paypal.service.PayoutService;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PayoutController
{
    private static final Logger LOG = LoggerFactory.getLogger(PayoutController.class);
    @Autowired
    private PayoutService payoutService;

    @GetMapping("/payout")
    public String payout(Model model){
        model.addAttribute("payoutDTO", new PayoutDTO());
        return "payout";
    }
    @PostMapping("/payout")
    public String pay(@ModelAttribute PayoutDTO payoutDTO)throws PayPalRESTException{
           payoutService.payout(payoutDTO);
       return "payout-success";
    }
}
