package com.dms.demo.services.subscription;

public interface SubscriptionService {

    String createPlusSubscriptionAndUpdateRolesAndGenerateInvoice(String id);

    String createPremiumSubscriptionAndUpdateRolesAndGenerateInvoice(String id);
}