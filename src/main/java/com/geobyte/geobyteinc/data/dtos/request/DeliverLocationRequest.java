package com.geobyte.geobyteinc.data.dtos.request;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliverLocationRequest {
    private Long originLocationId;
    private Long destinationLocationId;
}
