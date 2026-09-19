package com.amidosha.autotests.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:config.properties"
})
public interface ProjectConfig extends Config {

    @Key("api.baseUrl")
    String apiBaseUrl();

    @Key("ui.baseUrl")
    String uiBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("timeout")
    @DefaultValue("10000")
    long timeout();

    @Key("pageLoadTimeout")
    @DefaultValue("30000")
    long pageLoadTimeout();

    @Key("headless")
    @DefaultValue("true")
    boolean headless();
}
