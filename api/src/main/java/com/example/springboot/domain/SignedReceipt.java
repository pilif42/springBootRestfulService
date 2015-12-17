package com.example.springboot.domain;


import com.example.springboot.error.OurException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Getter
@Setter
@ToString
@Slf4j
@JsonDeserialize(using = SignedReceipt.SignedReceiptDeserializer.class)
public class SignedReceipt {

    @JsonProperty("data_signature")
    private String signature;

    private Receipt receipt;

    @JsonIgnore
    private String rawReceipt;

    public static class SignedReceiptDeserializer extends JsonDeserializer<SignedReceipt> {

        @Override
        public SignedReceipt deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            SignedReceipt receipt = new SignedReceipt();

            JsonNode node = jsonParser.getCodec().readTree(jsonParser);

            if (!node.has("data_signature")) {
                throw new OurException(OurException.Fault.DATA_SIGNATURE_NOT_FOUND);
            }
            receipt.signature = node.get("data_signature").asText();

            String nodeName = "purchase_data";
            if (node.has("receipt")) {
                nodeName = "receipt";
            }
            if (!node.has(nodeName)) {
                throw new OurException(OurException.Fault.RAW_RECEIPT_NOT_FOUND);
            }
            receipt.rawReceipt = node.get(nodeName).toString();

            try {
                receipt.receipt = jsonParser.getCodec().treeToValue(node.get(nodeName), Receipt.class);
            } catch (JsonProcessingException e) {
                log.error("JsonProcessingException with message = = {}", e.getMessage());
                throw new OurException(OurException.Fault.ERROR_PARSING_RECEIPT);
            }

            return receipt;
        }
    }
}
