package com.example.springboot.error;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

@JsonSerialize(using = OurException.OurExceptionSerializer.class)
public class OurException extends RuntimeException {

    public static enum Fault {
        CUSTOMER_NOT_FOUND(404, "UA-GP-102", "Customer details not found"),
        DATA_SIGNATURE_NOT_FOUND(500, "AS-GO-100", "Unable to find field 'data_signature'"),
        PURCHASE_DATA_NOT_FOUND(500, "AS-GO-100", "Unable to find field for 'purchase_data'"),
        ERROR_PARSING_PURCHASE_DATA(500, "AS-GO-100", "Unable to parse field 'purchase_data'"),
        ERROR_PARSING_RECEIPT_REQUEST(500, "AS-GO-100", "Failed to parse receipt request");

        private final int httpStatus;
        private final String message;
        private final String code;

        Fault(int httpStatus, String code, String message) {
            this.httpStatus = httpStatus;
            this.message = message;
            this.code = code;
        }

        public int getHttpStatus() {
            return httpStatus;
        }
        public String getMessage() {
            return message;
        }
        public String getCode() {
            return code;
        }
    }

    private Fault fault;
    private long timestamp;

    public OurException(Fault fault) {
        this(fault, null);
    }

    public OurException(Fault fault, Throwable cause) {
        super(fault.message, cause);
        this.fault = fault;
        this.timestamp = System.currentTimeMillis();
    }

    public Fault getFault() {
        return fault;
    }
    public long getTimestamp() {
        return timestamp;
    }

    public static class OurExceptionSerializer extends JsonSerializer<OurException> {
        @Override
        public void serialize(OurException value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeFieldName("error");
            jgen.writeStartObject();
            jgen.writeStringField("code", value.getFault().code);
            jgen.writeNumberField("timestamp", value.getTimestamp());

            if (value.getMessage() != null) {
                jgen.writeStringField("message", value.getMessage());
            }

            jgen.writeEndObject();
            jgen.writeEndObject();
        }
    }

}
