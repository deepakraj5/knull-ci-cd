package org.knullci.knull.infrastructure.service;

import org.knullci.knull.infrastructure.dto.UpdateCommitStatusDto;

public interface GithubService {
    void updateCommitStatus(UpdateCommitStatusDto updateCommitStatus);
}
