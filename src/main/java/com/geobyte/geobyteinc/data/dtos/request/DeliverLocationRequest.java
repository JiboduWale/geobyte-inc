package com.geobyte.geobyteinc.data.dtos.request;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliverLocationRequest {
    private Long originLocationId;
    private Long destinationLocationId;
}
