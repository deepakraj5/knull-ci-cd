package org.knullci.knull.web.controller;

import org.knullci.knull.application.command.GithubWebhookCommand;
import org.knullci.knull.application.dto.GithubWebhookRequestDto;
import org.knullci.knull.application.interfaces.GithubWebhookCommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webhook")
public class WebhookController {

    private final GithubWebhookCommandHandler githubWebhookCommandHandler;

    public WebhookController(GithubWebhookCommandHandler githubWebhookCommandHandler) {
        this.githubWebhookCommandHandler = githubWebhookCommandHandler;
    }

    @PostMapping("/github")
    public ResponseEntity<?> githubWebhook(@RequestBody GithubWebhookRequestDto request) {
        var command = new GithubWebhookCommand(request);

        githubWebhookCommandHandler.handle(command);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
