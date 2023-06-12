package com.dms.demo.repositories;

import com.dms.demo.models.entities.Subscription;
import com.dms.demo.util.enums.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    Subscription findBySubscriptionType(SubscriptionType subscriptionType);
}