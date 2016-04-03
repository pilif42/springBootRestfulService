package com.example.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("product_id")
  private String productId;

  @JsonProperty("start_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
  private Date startDate;

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String orderId;
    private String productId;
    private Date startDate;

    private Builder() {
    }

    public Builder withOrderId(String orderId) {
      this.orderId = orderId;
      return this;
    }

    public Builder withProductId(String productId) {
      this.productId = productId;
      return this;
    }

    public Builder withStartDate(Date startDate) {
      this.startDate = startDate;
      return this;
    }

    public Order build() {
      Order order = new Order();
      order.setOrderId(orderId);
      order.setProductId(productId);
      order.setStartDate(startDate);
      return order;
    }

  }
}
