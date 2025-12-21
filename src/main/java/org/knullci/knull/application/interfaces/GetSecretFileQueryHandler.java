package org.knullci.knull.application.interfaces;

import org.knullci.knull.application.dto.SecretFileDto;
import org.knullci.knull.application.query.GetSecretFileQuery;

public interface GetSecretFileQueryHandler {
    SecretFileDto handle(GetSecretFileQuery query);
}
