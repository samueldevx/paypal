package com.paypal.repos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "user")
public class User
{
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    private String paypalAuthorizationCode;


    @Embedded
    private PaypalUserInfo paypalUserInfo;
    @Embedded
    private PaypalTokenInfo paypalTokenInfo;


    public User(String paypalAuthorizationCode)
    {
        this.paypalAuthorizationCode = paypalAuthorizationCode;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getPaypalAuthorizationCode()
    {
        return paypalAuthorizationCode;
    }


    public void setPaypalAuthorizationCode(String paypalAuthorizationCode)
    {
        this.paypalAuthorizationCode = paypalAuthorizationCode;
    }

    public PaypalUserInfo getPaypalUserInfo()
    {
        return paypalUserInfo;
    }


    public void setPaypalUserInfo(PaypalUserInfo paypalUserInfo)
    {
        this.paypalUserInfo = paypalUserInfo;
    }


    public PaypalTokenInfo getPaypalTokenInfo()
    {
        return paypalTokenInfo;
    }


    public void setPaypalTokenInfo(PaypalTokenInfo paypalTokenInfo)
    {
        this.paypalTokenInfo = paypalTokenInfo;
    }
}

