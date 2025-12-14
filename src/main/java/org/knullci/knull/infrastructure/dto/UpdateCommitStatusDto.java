package org.knullci.knull.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.knullci.knull.infrastructure.enums.GHCommitState;

@Getter
@AllArgsConstructor
public class UpdateCommitStatusDto {
    private String owner;
    private String repo;
    private String commitSha;
    private GHCommitState commitState;
    private String targetUrl;
    private String description;
    private String context;
}
