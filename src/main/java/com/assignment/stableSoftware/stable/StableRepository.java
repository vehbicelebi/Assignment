package com.assignment.stableSoftware.stable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StableRepository extends JpaRepository<Stable, Long> {
}
