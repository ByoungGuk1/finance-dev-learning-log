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
public class ShinhanProperties2 {
    String api1;
    String api2;
//    String api3;

    @Setter
    @Getter
    @ToString
    @ConfigurationProperties(prefix = "shinhan")
    public static class ApiClass {
        String key1;
        String key2;
    }
}
