package com.geobyte.geobyteinc.repository;

import com.geobyte.geobyteinc.data.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
