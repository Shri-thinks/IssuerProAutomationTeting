package com.issuerpro.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseRequest {

    private String card_id;
    private Double amount;
    private String merchant_name;
    private String merchant_category;
    private Boolean international;

    public PurchaseRequest() {}

    public PurchaseRequest(String card_id, Double amount, String merchant_name, String merchant_category, Boolean international) {
        this.card_id = card_id;
        this.amount = amount;
        this.merchant_name = merchant_name;
        this.merchant_category = merchant_category;
        this.international = international;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getMerchant_category() {
        return merchant_category;
    }

    public void setMerchant_category(String merchant_category) {
        this.merchant_category = merchant_category;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }
}
