package com.amidosha.autotests.config;

import org.aeonbits.owner.ConfigFactory;

public final class Configs {

    private static final ProjectConfig CONFIG = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    private Configs() {
    }

    public static ProjectConfig config() {
        return CONFIG;
    }
}
