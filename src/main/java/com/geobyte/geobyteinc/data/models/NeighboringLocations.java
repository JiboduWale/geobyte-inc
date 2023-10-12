package com.geobyte.geobyteinc.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "neighboring_location")
public class NeighboringLocations {
    @Id
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "transaction_id")
//    private Location location;
}
