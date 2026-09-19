package com.amidosha.autotests.api.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.amidosha.autotests.config.Configs.config;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public final class ApiSpecs {

    private ApiSpecs() {
    }

    public static RequestSpecification request() {
        return new RequestSpecBuilder()
                .setBaseUri(config().apiBaseUrl())
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.BODY)
                .build();
    }

    public static ResponseSpecification success() {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(200), is(201)))
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
    }

    public static ResponseSpecification noContent() {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(200), is(204)))
                .log(LogDetail.STATUS)
                .build();
    }
}
