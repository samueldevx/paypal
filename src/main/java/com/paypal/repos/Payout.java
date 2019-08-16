package com.paypal.repos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "payout")
public class Payout
{
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    @Id
    @SequenceGenerator(name = "payout_id_seq", sequenceName = "payout_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payout_id_seq")
    private Long id;
    @Embedded
    private PaypalPayoutBatch paypalPayoutBatch;


    public Payout()
    {
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


    public PaypalPayoutBatch getPaypalPayoutBatch()
    {
        return paypalPayoutBatch;
    }


    public void setPaypalPayoutBatch(PaypalPayoutBatch paypalPayoutBatch)
    {
        this.paypalPayoutBatch = paypalPayoutBatch;
    }
}
