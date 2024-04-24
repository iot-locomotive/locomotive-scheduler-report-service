package com.iot.reportscheduler.models;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Document("loco_collection")
public class Locomotive {
    @Id
    private ObjectId id;
    private UUID locomotiveCode;
    private String locomotiveName;
    private String locomotiveDimension;
    private int status;
    private Instant time;
}
