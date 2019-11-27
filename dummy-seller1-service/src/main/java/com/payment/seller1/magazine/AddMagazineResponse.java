package com.payment.seller1.magazine;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AddMagazineResponse {

    private String id;
    private String title;
    private List<String> scientificFields;
    private int count;

}
