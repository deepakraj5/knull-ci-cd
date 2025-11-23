package org.knullci.knull.application.interfaces;

import org.knullci.knull.application.dto.JobDto;
import org.knullci.knull.application.query.GetAllJobQuery;

import java.util.List;

public interface GetAllQueryHandler extends QueryHandler<GetAllJobQuery, List<JobDto>> {
}
