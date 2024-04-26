package com.iot.reportscheduler.services;

import com.iot.reportscheduler.bot.LocomotiveBot;
import com.iot.reportscheduler.models.LocomotiveSummary;
import com.iot.reportscheduler.repository.LocomotiveRepository;
import com.iot.reportscheduler.repository.LocomotiveSummaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduleReportTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduleReportTask.class);

    @Autowired
    private LocomotiveRepository locomotiveRepository;

    @Autowired
    private LocomotiveSummaryRepository locomotiveSummaryRepository;

    @Autowired
    private LocomotiveBot locomotiveBot;

    @Scheduled(fixedRate = 10000)
    public void summaryReport() {
        // read all loco data from mongodb
        long totalLoc = locomotiveRepository.count();
        long totalLocMaintenance = locomotiveRepository.countByStatus(1);
        long totalLocTransit = locomotiveRepository.countByStatus(2);
        long totalLocDeparture = locomotiveRepository.countByStatus(3);

        // create summary locomotive (totalLoco, totalUnderMaintenance, totalTransitAtStation, totalDeparture)
        LocomotiveSummary locomotiveSummary = LocomotiveSummary.builder()
                .totalLoc((int) totalLoc)
                .totalLocMaintenance((int) totalLocMaintenance)
                .totalLocTransit((int) totalLocTransit)
                .totalLocDeparture((int) totalLocDeparture)
                .build();

        // insert summary loco to db postgres
        log.info("Start Saving Locomotive Summary to DB Postgres");

        LocomotiveSummary locomotiveSummarySaved = locomotiveSummaryRepository.save(locomotiveSummary);
        log.info(locomotiveSummarySaved.toString());

        log.info("Finish Saving Locomotive Summary to DB Postgres");

        // send summary loco via bot telegram
        Instant currentTimeUTC = Instant.now();
        ZoneId zone = ZoneId.of("Asia/Jakarta");
        ZonedDateTime localTime = currentTimeUTC.atZone(zone);
        String currentTime = localTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss XXX"));

        String summary = """
                <b>LOCOMOTIVE SUMMARY REPORT</b>
                <b><i>(%s)</i></b>

                Total Under Maintenance: %d
                Total Transit at Station: %d
                Total Departure: %d

                Total Locomotive: %d
                """.formatted(currentTime, totalLocMaintenance, totalLocTransit, totalLocDeparture, totalLoc);

        locomotiveBot.sendTextMessage(summary);
    }
}
