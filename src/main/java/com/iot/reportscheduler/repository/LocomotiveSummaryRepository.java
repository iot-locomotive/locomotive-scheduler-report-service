package com.iot.reportscheduler.repository;

import com.iot.reportscheduler.models.LocomotiveSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocomotiveSummaryRepository extends JpaRepository<LocomotiveSummary, Integer> {
    @Query(
            value = "SELECT * FROM locomotive_summary ls ORDER BY ls.summary_id DESC LIMIT 1",
            nativeQuery = true
    )
    LocomotiveSummary getLastLocomotiveSummary();
}
