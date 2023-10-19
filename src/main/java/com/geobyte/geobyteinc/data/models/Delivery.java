package com.geobyte.geobyteinc.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
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
    private Double weight;
    private LocalDateTime localDateTime;



  public double computeWeight(double lat1, double lon1, double lat2, double lon2) {
    final int R = 6371; // Radius of the earth

    // Convert latitude and longitude from degrees to radians
    double lat1Rad = Math.toRadians(lat1);
    double lon1Rad = Math.toRadians(lon1);
    double lat2Rad = Math.toRadians(lat2);
    double lon2Rad = Math.toRadians(lon2);

    // Haversine formula
    double dLat = lat2Rad - lat1Rad;
    double dLon = lon2Rad - lon1Rad;
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
      + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    // Calculate the distance
    return R * c;
  }
}
