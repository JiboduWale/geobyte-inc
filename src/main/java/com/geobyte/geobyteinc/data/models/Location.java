package com.geobyte.geobyteinc.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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


  @Override
  public int hashCode() {
    return Objects.hash(id, name, longitude, latitude);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Location location = (Location) obj;
    return id.equals(location.id) &&
      name.equals(location.name) &&
      Double.compare(location.longitude, longitude) == 0 &&
      Double.compare(location.latitude, latitude) == 0;
  }


}
