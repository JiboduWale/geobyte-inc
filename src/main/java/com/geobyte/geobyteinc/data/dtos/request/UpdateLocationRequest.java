package com.geobyte.geobyteinc.data.dtos.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateLocationRequest {
    private Long id;
    private String name;
    private Double Longitude;
    private Double Latitude;
}
