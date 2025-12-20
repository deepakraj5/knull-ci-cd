package org.knullci.knull.application.handler;

import lombok.SneakyThrows;
import org.knullci.knull.application.command.GithubWebhookCommand;
import org.knullci.knull.application.constant.KnullConstant;
import org.knullci.knull.application.dto.GithubWebhookResponseDto;
import org.knullci.knull.application.interfaces.GithubWebhookCommandHandler;
import org.knullci.knull.domain.repository.JobRepository;
import org.knullci.knull.infrastructure.dto.UpdateCommitStatusDto;
import org.knullci.knull.infrastructure.enums.GHCommitState;
import org.knullci.knull.infrastructure.service.GithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GithubWebhookCommandHandlerImpl implements GithubWebhookCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(GithubWebhookCommandHandlerImpl.class);

    private final GithubService githubService;
    private final JobRepository jobRepository;

    public GithubWebhookCommandHandlerImpl(GithubService githubService, JobRepository jobRepository) {
        this.githubService = githubService;
        this.jobRepository = jobRepository;
    }

    @Override
    @SneakyThrows
    public GithubWebhookResponseDto handle(GithubWebhookCommand command) {

        // check if the job available for the incoming repo
        String repoName = command.getGithubWebhook().getRepository().getName();
        var job = this.jobRepository.getJobByRepoName(repoName);
        if (job.isEmpty()) {
            logger.info("No job found for repo: {}, skipping pipeline", repoName);
            return null;
        }

        // update the commit status to PENDING
        this.githubService.updateCommitStatus(new UpdateCommitStatusDto(
                command.getGithubWebhook().getRepository().getOwner().getName(),
                command.getGithubWebhook().getRepository().getName(),
                command.getGithubWebhook().getHeadCommit().getId(),
                GHCommitState.PENDING,
                "http://localhost:8080/builds",
                KnullConstant.BUILD_PENDING_DESCRIPTION,
                KnullConstant.BUILD_CONTEXT
        ));

        return null;
    }
}
