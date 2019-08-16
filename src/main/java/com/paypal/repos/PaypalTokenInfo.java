package com.paypal.repos;

import com.paypal.api.openidconnect.Tokeninfo;

import javax.persistence.Embeddable;

@Embeddable
public class PaypalTokenInfo
{
    private String scope;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Integer expiresIn;
    private String appId;

    public PaypalTokenInfo(Tokeninfo tokeninfo){
        this.scope = tokeninfo.getScope();
        this.accessToken = tokeninfo.getAccessToken();
        this.refreshToken = tokeninfo.getRefreshToken();
        this.tokenType = tokeninfo.getTokenType();
        this.expiresIn = tokeninfo.getExpiresIn();
        this.appId = tokeninfo.getAppId();
    }


    public PaypalTokenInfo(String scope, String accessToken, String refreshToken, String tokenType, Integer expiresIn, String appId)
    {
        this.scope = scope;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.appId = appId;
    }


    public String getScope()
    {
        return scope;
    }


    public void setScope(String scope)
    {
        this.scope = scope;
    }


    public String getAccessToken()
    {
        return accessToken;
    }


    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }


    public String getRefreshToken()
    {
        return refreshToken;
    }


    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }


    public String getTokenType()
    {
        return tokenType;
    }


    public void setTokenType(String tokenType)
    {
        this.tokenType = tokenType;
    }


    public Integer getExpiresIn()
    {
        return expiresIn;
    }


    public void setExpiresIn(Integer expiresIn)
    {
        this.expiresIn = expiresIn;
    }


    public String getAppId()
    {
        return appId;
    }


    public void setAppId(String appId)
    {
        this.appId = appId;
    }
}
