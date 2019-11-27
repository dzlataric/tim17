package com.payment.seller1.magazine;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Magazine {

    private String id;
    private String title;
    private List<String> scientificFields;

    public Magazine(String id, String title, List<String> scientificFields) {
        this.id = id;
        this.title = title;
        this.scientificFields = scientificFields;
    }
}
