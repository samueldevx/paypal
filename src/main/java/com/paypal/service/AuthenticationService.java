package com.paypal.service;

import com.paypal.repos.User;

public interface AuthenticationService
{
    public void signup(String username,String password);
    public User saveUserWithAuthorizationCode(String code,String scope) ;
    public void handleReturnURLFromPaypal(String code,String scope) ;
    public void getRedirectURL();
}
