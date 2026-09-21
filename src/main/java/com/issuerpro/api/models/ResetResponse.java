package com.issuerpro.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResetResponse {

    private String message;
    private Map<String, Integer> collections;

    public ResetResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Integer> getCollections() {
        return collections;
    }

    public void setCollections(Map<String, Integer> collections) {
        this.collections = collections;
    }
}
