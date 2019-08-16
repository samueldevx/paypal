package com.paypal.controller;

import com.paypal.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("signup") public String signup() {
//		authenticationService.getRedirectURL();
		return "signup";

	}


	/**
	 * this api to handle successfull login from user in paypal and paypal call the return url we provided
	 * this is the return URL provided in paypal
	 * @param code
	 * @param scope
	 * @return
	 */
	@PostMapping("authenticated") public void handleReturnURLFromPaypal(@RequestParam("code")String code,@RequestParam("scope")String scope) {
		authenticationService.handleReturnURLFromPaypal(code,scope);
	}
}
