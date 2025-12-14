package org.knullci.knull.infrastructure.service;

import org.knullci.knull.infrastructure.dto.UpdateCommitStatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GithubApiService implements GithubService {

    private final Logger logger = LoggerFactory.getLogger(GithubApiService.class);

    private final WebClient webClient;

    public GithubApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void updateCommitStatus(UpdateCommitStatusDto updateCommitStatus) {
        try {
            this.webClient.post()
                    .uri("/repos/{owner}/{repo}/statuses/{sha}",
                            updateCommitStatus.getOwner(), updateCommitStatus.getRepo(), updateCommitStatus.getCommitSha())
                    .bodyValue(Map.of(
                            "state", updateCommitStatus.getCommitState().toString().toLowerCase(),
                            "description", updateCommitStatus.getDescription(),
                            "context", updateCommitStatus.getContext(),
                            "target_url", updateCommitStatus.getTargetUrl()
                    ))
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            logger.error("Failed to update commit state: {}", e.toString());
        }
    }
}
