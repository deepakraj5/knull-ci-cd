package org.knullci.knull.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    /**
     * Primary ObjectMapper for JSON serialization.
     * This ensures JSON is used by default for REST endpoints.
     */
    @Bean
    @Primary
    public ObjectMapper jsonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.build();
    }

    /**
     * Separate ObjectMapper for YAML parsing (used for knull.yaml files).
     * Not marked as @Primary, so it won't be used for HTTP responses.
     */
    @Bean(name = "yamlObjectMapper")
    public YAMLMapper yamlObjectMapper() {
        return new YAMLMapper();
    }
}
