package com.geobyte.geobyteinc.repository;

import com.geobyte.geobyteinc.data.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
