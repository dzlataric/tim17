package com.payment.seller1.magazine;

import lombok.Builder;
import lombok.Value;

import java.util.List;

public class Magazine {

    private String id;
    private String title;
    private List<String> scientificFields;
    private Float subscriptionFee;

    public Magazine(String id, String title, List<String> scientificFields, Float subscriptionFee) {
        this.id = id;
        this.title = title;
        this.scientificFields = scientificFields;
        this.subscriptionFee = subscriptionFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getScientificFields() {
        return scientificFields;
    }

    public void setScientificFields(List<String> scientificFields) {
        this.scientificFields = scientificFields;
    }

    public Float getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(Float subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }
}
