package com.issuerpro.api;

import com.issuerpro.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

/**
 * Reusable RestAssured Request and Response specification factory.
 */
public class ApiSpecFactory {

    public static RequestSpecification defaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();
    }

    public static RequestSpecification authenticatedRequestSpec(Map<String, String> cookies) {
        return new RequestSpecBuilder()
                .addRequestSpecification(defaultRequestSpec())
                .addCookies(cookies)
                .build();
    }

    public static ResponseSpecification successResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .build();
    }
}
