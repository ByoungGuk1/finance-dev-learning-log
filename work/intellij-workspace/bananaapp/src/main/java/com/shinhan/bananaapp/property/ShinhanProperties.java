package com.shinhan.bananaapp.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ToString
@ConfigurationProperties(prefix = "shinhan")
public class ShinhanProperties {
    ApiClass api1;
    ApiClass api2;
//    ApiClass api3;

    @Setter
    @Getter
    @ToString
    public static class ApiClass {
        String key1;
        String key2;
    }
}
