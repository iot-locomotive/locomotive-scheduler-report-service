package com.iot.reportscheduler.repository;

import com.iot.reportscheduler.models.Locomotive;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocomotiveRepository extends MongoRepository<Locomotive, ObjectId> {
    long countByStatus(int status);
}
