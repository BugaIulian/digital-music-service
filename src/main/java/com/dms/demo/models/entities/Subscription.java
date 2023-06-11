package com.dms.demo.models.entities;

import com.dms.demo.util.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "subscription_type")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(name = "price")
    private double price;

    @Column(name = "features")
    private String features;
}