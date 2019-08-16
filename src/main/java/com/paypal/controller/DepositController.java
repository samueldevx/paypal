package com.paypal.controller;

import com.paypal.dto.DepositDTO;
import com.paypal.service.DepositService;
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
public class DepositController
{
    private static final Logger LOG = LoggerFactory.getLogger(DepositController.class);
    @Autowired
    private DepositService depositService;
    @GetMapping("/deposit")
    public String payout(Model model){
        model.addAttribute("depositDTO", new DepositDTO());
        return "deposit";
    }
    @PostMapping("/deposit")
    public String pay(@ModelAttribute DepositDTO depositDTO)throws PayPalRESTException
    {
        depositService.deposit(depositDTO);
        return "deposit-success";
    }
}
