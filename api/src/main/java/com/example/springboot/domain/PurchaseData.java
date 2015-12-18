package com.example.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the inbound - i.e. device originated - google play receipt
 *
 * http://developer.android.com/google/play/billing/billing_reference.html
 */
@Getter
@Setter
@ToString
public class PurchaseData {

    public enum PurchaseState {
        PURCHASED,  // 0
        CANCELED,   // 1
        REFUNDED    // 2
    }

    private Boolean autoRenewing;
    private String orderId;
    private String packageName;
    private String productId;
    private Long purchaseTime;
    private PurchaseState purchaseState;
    private String purchaseToken;
    private String developerPayload;

}

