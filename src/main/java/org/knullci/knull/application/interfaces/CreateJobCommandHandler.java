package org.knullci.knull.application.interfaces;

import org.knullci.knull.application.command.CreateJobCommand;
import org.knullci.knull.application.dto.CreateJobResponseDto;

public interface CreateJobCommandHandler extends CommandHandler<CreateJobCommand, CreateJobResponseDto> {

}
