package com.amidosha.autotests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(Integer id, String name, String username, String email, Address address) {
}
