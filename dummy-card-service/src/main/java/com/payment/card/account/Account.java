package com.payment.card.account;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private String accountNumber;
    private String cardPAN;
    private String cardHolderName;
    private String cvv;
    private LocalDate cardValidThru;
    private Double balance;

    public boolean checkCardDetails(String pan, String chn, String cvv, LocalDate validThru) {
        return cardPAN.equals(pan.trim()) && cardHolderName.equals(chn.trim())
                && this.cvv.equals(cvv.trim()) && cardValidThru.equals(validThru);
    }

}
