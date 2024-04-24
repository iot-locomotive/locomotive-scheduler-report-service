package com.iot.reportscheduler.services;

import com.iot.reportscheduler.dtos.GenericResponseDto;
import com.iot.reportscheduler.models.LocomotiveSummary;

public interface LocomotiveSummaryService {
    GenericResponseDto<LocomotiveSummary> getLocomotiveSummary();
}
