package com.geobyte.geobyteinc.data.dtos.request;


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
