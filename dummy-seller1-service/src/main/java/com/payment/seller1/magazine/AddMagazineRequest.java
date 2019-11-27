package com.payment.seller1.magazine;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AddMagazineRequest {

    private String id;
    private String title;
    private List<String> scientificFields;

}
