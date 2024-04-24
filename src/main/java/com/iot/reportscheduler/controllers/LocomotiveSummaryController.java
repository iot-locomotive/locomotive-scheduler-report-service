package com.iot.reportscheduler.controllers;

import com.iot.reportscheduler.dtos.GenericResponseDto;
import com.iot.reportscheduler.models.LocomotiveSummary;
import com.iot.reportscheduler.services.LocomotiveSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/locomotive-summary")
public class LocomotiveSummaryController {
    private final LocomotiveSummaryService locomotiveSummaryService;

    @Autowired
    public LocomotiveSummaryController(@Qualifier("v1") LocomotiveSummaryService locomotiveSummaryService) {
        this.locomotiveSummaryService = locomotiveSummaryService;
    }

    @GetMapping
    public ResponseEntity<GenericResponseDto<LocomotiveSummary>> getLocomotiveSummary() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locomotiveSummaryService.getLocomotiveSummary());
    }
}
