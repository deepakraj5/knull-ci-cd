package org.knullci.knull.application.handler;

import lombok.SneakyThrows;
import org.knullci.knull.application.command.GithubWebhookCommand;
import org.knullci.knull.application.dto.GithubWebhookResponseDto;
import org.knullci.knull.application.interfaces.GithubWebhookCommandHandler;
import org.knullci.knull.infrastructure.dto.UpdateCommitStatusDto;
import org.knullci.knull.infrastructure.enums.GHCommitState;
import org.knullci.knull.infrastructure.service.GithubService;
import org.springframework.stereotype.Service;

@Service
public class GithubWebhookCommandHandlerImpl implements GithubWebhookCommandHandler {

    private final GithubService githubService;

    public GithubWebhookCommandHandlerImpl(GithubService githubService) {
        this.githubService = githubService;
    }

    @Override
    @SneakyThrows
    public GithubWebhookResponseDto handle(GithubWebhookCommand command) {

        this.githubService.updateCommitStatus(new UpdateCommitStatusDto(
                command.getGithubWebhook().getRepository().getOwner().getName(),
                command.getGithubWebhook().getRepository().getName(),
                command.getGithubWebhook().getHeadCommit().getId(),
                GHCommitState.PENDING,
                "http://localhost:8080/builds",
                "Building",
                "ci/knull"
        ));

        return null;
    }
}
