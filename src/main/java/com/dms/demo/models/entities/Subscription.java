package com.dms.demo.models.entities;

import com.dms.demo.util.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(name = "id", length = 26)
    private String id;

    @Column(name = "subscription_type")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(name = "price")
    private double price;

    @Column(name = "features")
    private String features;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<User> users = new ArrayList<>();

    public Subscription() {
        this.id = new ULID().nextULID();
    }
}