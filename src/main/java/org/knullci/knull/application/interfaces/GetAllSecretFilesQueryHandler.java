package org.knullci.knull.application.interfaces;

import org.knullci.knull.application.dto.SecretFileDto;
import org.knullci.knull.application.query.GetAllSecretFilesQuery;

import java.util.List;

public interface GetAllSecretFilesQueryHandler {
    List<SecretFileDto> handle(GetAllSecretFilesQuery query);
}
