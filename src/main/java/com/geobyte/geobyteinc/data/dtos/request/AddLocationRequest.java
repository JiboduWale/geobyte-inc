package com.geobyte.geobyteinc.data.dtos.request;

import com.geobyte.geobyteinc.data.models.Location;
import com.geobyte.geobyteinc.data.models.NeighboringLocations;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddLocationRequest {
    private String name;
    private Double Longitude;
    private Double Latitude;
}
