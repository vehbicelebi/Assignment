package com.assignment.stableSoftware.feedingTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedingTimeRepository extends JpaRepository<FeedingTime, Long> {
}
