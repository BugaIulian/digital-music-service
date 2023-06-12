package com.dms.demo.controllers;

import com.dms.demo.services.subscription.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/listen-plus-subscription/{id}")
    public ResponseEntity<String> subscribeToListenPlus(@PathVariable String id) {
        return ResponseEntity.ok(subscriptionService.createPlusSubscriptionAndUpdateRolesAndGenerateInvoice(id));
    }

    @PostMapping("/listen-premium-subscription/{id}")
    public ResponseEntity<String> subscribeToPremium(@PathVariable String id) {
        return ResponseEntity.ok(subscriptionService.createPremiumSubscriptionAndUpdateRolesAndGenerateInvoice(id));
    }
}