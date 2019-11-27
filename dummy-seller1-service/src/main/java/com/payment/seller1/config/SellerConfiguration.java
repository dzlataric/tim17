package com.payment.seller1.config;

import com.payment.seller1.magazine.Magazine;
import com.payment.seller1.magazine.MagazineConfiguration;
import com.payment.seller1.order.Order;
import com.payment.seller1.order.OrderConfiguration;
import com.payment.seller1.user.User;
import com.payment.seller1.user.UserConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SellerConfiguration {

    private Map<String, User> userConfigurationMap = new HashMap<>();
    private Map<String, Magazine> magazineConfigurationMap = new HashMap<>();
    private Map<String, Order> orderConfigurationMap = new HashMap<>();

    // init magazines for testing
    private void initMagazines() {

        List<String> scientificFields = new ArrayList<String>();
        scientificFields.add("physics");

        magazineConfigurationMap.put("123", new Magazine("123", "Magazine1", scientificFields));
        magazineConfigurationMap.put("124", new Magazine("124", "Magazine2", scientificFields));
        magazineConfigurationMap.put("125", new Magazine("125", "Magazine3", scientificFields));

    }

    @Bean
    public UserConfiguration userConfiguration() {
        return UserConfiguration.builder().users(userConfigurationMap).build();
    }

    @Bean
    public MagazineConfiguration magazineConfiguration() {
        initMagazines();
        return MagazineConfiguration.builder().magazines(magazineConfigurationMap).build();
    }

    @Bean
    public OrderConfiguration orderConfiguration() {
        return OrderConfiguration.builder().orders(orderConfigurationMap).build();
    }

}
