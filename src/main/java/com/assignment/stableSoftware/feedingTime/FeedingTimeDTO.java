package com.assignment.stableSoftware.feedingTime;

import com.assignment.stableSoftware.operation.Operation;

import java.time.LocalTime;

public class FeedingTimeDTO {
    private LocalTime localTime;
    private Operation operation;

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
