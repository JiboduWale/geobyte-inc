package com.geobyte.geobyteinc.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;
//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<NeighboringLocations> neighbors = new ArrayList<>();



    public double getDistanceTo(Location otherLocation) {

        // Return the distance in kilometers
        return distanceInKilometers(this.getLatitude(), this.getLongitude(),
                otherLocation.getLatitude(), otherLocation.getLongitude());
    }

    // Haversine formula for calculating distance between two coordinates
    private double distanceInKilometers(double lat1, double lon1, double lat2, double lon2) {
        // Radius of the Earth in kilometers
        final double R = 6371.0;

        // Convert latitude and longitude from degrees to radians
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2.0), 2.0) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2.0), 2.0);
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

        // Calculate the distance in kilometers
        return R * c;
    }

//    public List<NeighboringLocations> getNeighbors() {
//        return neighbors;
//    }

}
