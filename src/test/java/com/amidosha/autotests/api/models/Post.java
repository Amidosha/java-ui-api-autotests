package com.amidosha.autotests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Post(Integer userId, Integer id, String title, String body) {
}
