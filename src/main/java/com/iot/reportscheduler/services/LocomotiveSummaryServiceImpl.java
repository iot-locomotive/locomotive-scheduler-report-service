package com.iot.reportscheduler.services;

import com.iot.reportscheduler.dtos.GenericResponseDto;
import com.iot.reportscheduler.models.LocomotiveSummary;
import com.iot.reportscheduler.repository.LocomotiveSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("v1")
public class LocomotiveSummaryServiceImpl implements LocomotiveSummaryService {

    @Autowired
    private LocomotiveSummaryRepository locomotiveSummaryRepository;

    @Override
    public GenericResponseDto<LocomotiveSummary> getLocomotiveSummary() {
        return GenericResponseDto.<LocomotiveSummary>builder()
                .data(locomotiveSummaryRepository.getLastLocomotiveSummary())
                .message("Success Get Locomotive Summary Data")
                .build();
    }
}
