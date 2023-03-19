package com.springboot.demo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeaturesEndpointActuator {

    private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();

    public FeaturesEndpointActuator() {
        featureMap.put("Departments", new Feature(true));
        featureMap.put("Users", new Feature(false));
        featureMap.put("Auth", new Feature(false));
    }

    @ReadOperation
    public Map<String, Feature> getAllFeatures() {
        return featureMap;
    }

    @ReadOperation
    public Feature getFeature(@Selector String featureName) {
        return featureMap.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature {
        private Boolean isEnabled;
    }

}
