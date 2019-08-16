package com.paypal.repos;

import com.paypal.api.payments.*;
import com.paypal.api.payments.Error;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "payment")
public class PaypalPayment
{
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    @Id
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    private Long id;

}
