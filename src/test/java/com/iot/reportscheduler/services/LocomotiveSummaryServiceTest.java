package com.iot.reportscheduler.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iot.reportscheduler.models.LocomotiveSummary;
import com.iot.reportscheduler.repository.LocomotiveSummaryRepository;

@ExtendWith(MockitoExtension.class)
public class LocomotiveSummaryServiceTest {

    @Mock
    private LocomotiveSummaryRepository locomotiveSummaryRepository;

    @InjectMocks
    private LocomotiveSummaryServiceImpl locomotiveSummaryService;

    @Test
    public void getLocomotiveSummaryTest() throws Exception {
        // save test

        LocomotiveSummary locomotiveSummary = LocomotiveSummary.builder()
                .totalLoc(1)
                .totalLocMaintenance(1)
                .totalLocTransit(0)
                .totalLocDeparture(0)
                .createdDate(Instant.now())
                .build();

        when(locomotiveSummaryRepository.save(any(LocomotiveSummary.class))).thenReturn(locomotiveSummary);

        LocomotiveSummary resultSaved = locomotiveSummaryRepository.save(locomotiveSummary);

        verify(locomotiveSummaryRepository, times(1)).save(any(LocomotiveSummary.class));

        assertEquals(locomotiveSummary.getTotalLoc(), resultSaved.getTotalLoc());
        assertEquals(locomotiveSummary.getCreatedDate(), resultSaved.getCreatedDate());

        // get test

        when(locomotiveSummaryRepository.getLastLocomotiveSummary()).thenReturn(locomotiveSummary);

        LocomotiveSummary result = locomotiveSummaryService.getLocomotiveSummary().getData();

        assertEquals(locomotiveSummary.getTotalLoc(), result.getTotalLoc());
        assertEquals(locomotiveSummary.getCreatedDate(), result.getCreatedDate());

        verify(locomotiveSummaryRepository, times(1)).getLastLocomotiveSummary();
    }

}
