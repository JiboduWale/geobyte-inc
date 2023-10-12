package com.geobyte.geobyteinc.repository;

import com.geobyte.geobyteinc.data.models.NeighboringLocations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeighboringLocationRepository extends JpaRepository<NeighboringLocations, Long> {
}
