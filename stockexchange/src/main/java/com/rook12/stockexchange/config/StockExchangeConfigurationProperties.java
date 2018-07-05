package com.rook12.stockexchange.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@org.springframework.boot.context.properties.ConfigurationProperties(prefix = "stockexchange")
public class StockExchangeConfigurationProperties {

    private String name;
    private String geometricmeanmethod;

    public String getGeometricmeanmethod() {
        return geometricmeanmethod;
    }

    public void setGeometricmeanmethod(String geometricmeanmethod) {
        this.geometricmeanmethod = geometricmeanmethod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
