package com.dms.demo.services.subscription;

import com.dms.demo.exceptions.user.UserNotFoundException;
import com.dms.demo.models.entities.Role;
import com.dms.demo.models.entities.Subscription;
import com.dms.demo.models.entities.User;
import com.dms.demo.repositories.SubscriptionRepository;
import com.dms.demo.repositories.UserRepository;
import com.dms.demo.services.invoice.PDFInvoiceService;
import com.dms.demo.services.role.RoleService;
import com.dms.demo.util.enums.RoleType;
import com.dms.demo.util.enums.SubscriptionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final PDFInvoiceService pdfInvoiceService;
    private final SubscriptionRepository subscriptionRepository;
    private final RoleService roleService;

    public SubscriptionServiceImpl(UserRepository userRepository, PDFInvoiceService pdfInvoiceService, SubscriptionRepository subscriptionRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.pdfInvoiceService = pdfInvoiceService;
        this.subscriptionRepository = subscriptionRepository;
        this.roleService = roleService;
    }
//TO DO
    @Transactional
    @Override
    public String createPlusSubscriptionAndUpdateRolesAndGenerateInvoice(String id) {
        User userToBeSubscribed = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        Role basicRole = roleService.getOrCreateRole(RoleType.ROLE_BASIC_USER);
        userToBeSubscribed.getUserRoles().add(basicRole);
        Subscription plusSubscription = subscriptionRepository.findBySubscriptionType(SubscriptionType.LISTEN_PLUS);
        if (plusSubscription == null) {
            plusSubscription = new Subscription();
            plusSubscription.setSubscriptionType(SubscriptionType.LISTEN_PLUS);
            subscriptionRepository.save(plusSubscription);
        }
        plusSubscription.getUsers().add(userToBeSubscribed);
        userToBeSubscribed.setSubscription(plusSubscription);
        subscriptionRepository.save(plusSubscription);
        userRepository.save(userToBeSubscribed);
        return pdfInvoiceService.generateListenPlusInvoicePDF(userToBeSubscribed.getCity(), userToBeSubscribed.getFirstName(), userToBeSubscribed.getSecondName(), "1", 9.99);
    }

    @Transactional
    @Override
    public String createPremiumSubscriptionAndUpdateRolesAndGenerateInvoice(String id) {
        User userToBeSubscribed = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        Role premiumRole = roleService.getOrCreateRole(RoleType.ROLE_PREMIUM_USER);
        userToBeSubscribed.getUserRoles().add(premiumRole);
        Subscription premiumSubscription = subscriptionRepository.findBySubscriptionType(SubscriptionType.LISTEN_MAX);
        if (premiumSubscription == null) {
            premiumSubscription = new Subscription();
            premiumSubscription.setSubscriptionType(SubscriptionType.LISTEN_MAX);
            subscriptionRepository.save(premiumSubscription);
        }
        premiumSubscription.getUsers().add(userToBeSubscribed);
        userToBeSubscribed.setSubscription(premiumSubscription);
        userRepository.save(userToBeSubscribed);
        return pdfInvoiceService.generateListenPlusInvoicePDF(userToBeSubscribed.getCity(), userToBeSubscribed.getFirstName(), userToBeSubscribed.getSecondName(), "1", 9.99);
    }
}