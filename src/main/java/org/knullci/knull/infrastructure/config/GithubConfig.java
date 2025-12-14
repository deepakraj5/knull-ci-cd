package org.knullci.knull.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GithubConfig {

    @Value("${github.pat.token}")
    private String githubPat;

    @Value("${github.api.base-url}")
    private String baseUrl;

    @Bean
    public WebClient gitHub() {
        return WebClient.builder()
                .baseUrl(this.baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.githubPat)
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .defaultHeader(HttpHeaders.USER_AGENT, "knull-ci")
                .build();
    }

}
