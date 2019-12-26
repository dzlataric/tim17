package com.payment.cryptocurrencyservice.config;

import com.bitpay.sdk.BitPayException;
import com.bitpay.sdk.Client;
import com.bitpay.sdk.Env;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoPaymentConfig {

    @Value("${bitpay.merchant.token}")
    private String merchantToken;

    @Value("${bitpay.private.key.path}")
    private String keyPath;

    @Bean
    public Client bitpayClient() throws BitPayException {
        return new Client(
                Env.Test,
                keyPath,
                new Env.Tokens(){{
                    merchant = merchantToken;
                }}
        );
    }

}
