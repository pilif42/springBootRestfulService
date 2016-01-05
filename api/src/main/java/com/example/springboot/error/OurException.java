package com.example.springboot.error;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@JsonSerialize(using = OurException.OurExceptionSerializer.class)
public class OurException extends RuntimeException {

    public static enum Fault {
        CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "UA-GP-102", "Customer details not found"),
        DATA_SIGNATURE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "AS-GO-100", "Unable to find field 'data_signature'"),
        PURCHASE_DATA_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "AS-GO-100", "Unable to find field for 'purchase_data'"),
        ERROR_PARSING_PURCHASE_DATA(HttpStatus.INTERNAL_SERVER_ERROR, "AS-GO-100", "Unable to parse field 'purchase_data'"),
        ERROR_PARSING_RECEIPT_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR, "AS-GO-100", "Failed to parse receipt request"),
        ERROR_PARSING_JSON_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR, "AS-GO-100", "Failed to parse json request");

        private final HttpStatus httpStatus;
        private final String message;
        private final String code;

        Fault(HttpStatus httpStatus, String code, String message) {
            this.httpStatus = httpStatus;
            this.message = message;
            this.code = code;
        }

        public HttpStatus getHttpStatus() {
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
