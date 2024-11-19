package com.assignment.stableSoftware.feedingPreference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedingPreferenceRepository extends JpaRepository<FeedingPreference, Long> {
}
