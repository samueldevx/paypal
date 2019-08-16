package com.paypal.repos;

import com.paypal.api.openidconnect.Address;
import com.paypal.api.openidconnect.Userinfo;

import javax.persistence.Embeddable;

@Embeddable
public class PaypalUserInfo
{
    private String userId;
    private String sub;
    private String name;
    private String givenName;
    private String familyName;
    private String middleName;
    private String picture;
    private String email;
    private Boolean emailVerified;
    private String gender;
    private String birthday;
    private String zoneinfo;
    private String locale;
    private String phoneNumber;
    private PaypalUserAddress address;
    private Boolean verifiedAccount;
    private String accountType;
    private String ageRange;
    private String payerId;

    public PaypalUserInfo(Userinfo userinfo){
        this.userId = userinfo.getUserId();
        this.sub = userinfo.getSub();
        this.name = userinfo.getName();
        this.givenName = userinfo.getGivenName();
        this.familyName = userinfo.getFamilyName();
        this.middleName = userinfo.getMiddleName();
        this.picture = userinfo.getPicture();
        this.email = userinfo.getEmail();
        this.emailVerified = userinfo.getEmailVerified();
        this.gender = userinfo.getGender();
        this.birthday = userinfo.getBirthday();
        this.zoneinfo = userinfo.getZoneinfo();
        this.locale = userinfo.getLocale();
        this.phoneNumber = userinfo.getPhoneNumber();
        this.address = new PaypalUserAddress(userinfo.getAddress());
        this.verifiedAccount = userinfo.getVerifiedAccount();
        this.accountType = userinfo.getAccountType();
        this.ageRange = userinfo.getAgeRange();
        this.payerId = userinfo.getPayerId();
    }


    public PaypalUserInfo(
        String userId, String sub, String name, String givenName, String familyName, String middleName, String picture, String email, Boolean emailVerified, String gender,
        String birthday, String zoneinfo, String locale, String phoneNumber, Address address, Boolean verifiedAccount, String accountType, String ageRange, String payerId)
    {
        this.userId = userId;
        this.sub = sub;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.middleName = middleName;
        this.picture = picture;
        this.email = email;
        this.emailVerified = emailVerified;
        this.gender = gender;
        this.birthday = birthday;
        this.zoneinfo = zoneinfo;
        this.locale = locale;
        this.phoneNumber = phoneNumber;
        this.address = new PaypalUserAddress(address);
        this.verifiedAccount = verifiedAccount;
        this.accountType = accountType;
        this.ageRange = ageRange;
        this.payerId = payerId;
    }
}
