package com.assignment.stableSoftware.feedingTime;

import com.assignment.stableSoftware.horse.Horse;
import com.assignment.stableSoftware.operation.Operation;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class FeedingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horse horse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
