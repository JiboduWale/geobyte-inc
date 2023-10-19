package com.geobyte.geobyteinc.data.models;


import lombok.*;


import java.util.List;

@Data
@RequiredArgsConstructor
public class Graph {
  private final List<Location> locations;
  private final List<Delivery> neighboringLocations;
}
