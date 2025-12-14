package org.knullci.knull.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.knullci.knull.application.dto.GithubWebhookRequestDto;

@Getter
@AllArgsConstructor
public class GithubWebhookCommand {
    private GithubWebhookRequestDto githubWebhook;
}
