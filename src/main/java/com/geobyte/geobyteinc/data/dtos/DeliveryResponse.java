package com.geobyte.geobyteinc.data.dtos;

import com.geobyte.geobyteinc.data.models.Location;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse extends Response{
    private List<Location> optimalRoute;
    private Double cost;
}
