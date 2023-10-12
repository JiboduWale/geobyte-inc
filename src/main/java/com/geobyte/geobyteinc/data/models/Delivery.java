package com.geobyte.geobyteinc.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Data
@RequiredArgsConstructor
@Entity(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "origin_location_id")
    private Location originLocation;
    @ManyToOne
    @JoinColumn(name = "destination_location_id")
    private Location destinationLocation;
    @ManyToMany
    private List<Location> optimalRoute;
    private LocalDateTime localDateTime;
    private Double totalDistance;
    private Double totalCost;
}
