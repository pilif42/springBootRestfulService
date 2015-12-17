package com.example.springboot.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiptRequest {

    public final String receipt;

    @JsonCreator
    public ReceiptRequest(@JsonProperty("appstore_receipt") String receipt) {
        this.receipt = receipt;
    }

}
