package com.payment.gateway.config;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public List get() {

        List resources = new ArrayList<>();
        String version = "2.0";
        for (var name :  discoveryClient.getServices()) {
            if (name.equalsIgnoreCase(appName)) {
                continue;
            }
            String endpoint = String.format("/%s/v2/api-docs", name);
            resources.add(swaggerResource(name, endpoint, version));
        }

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
