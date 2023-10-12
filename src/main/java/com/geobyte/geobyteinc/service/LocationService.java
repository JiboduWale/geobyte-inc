package com.geobyte.geobyteinc.service;

import com.geobyte.geobyteinc.data.dtos.request.AddLocationRequest;
import com.geobyte.geobyteinc.data.dtos.Response;
import com.geobyte.geobyteinc.data.dtos.request.UpdateLocationRequest;
import com.geobyte.geobyteinc.data.models.Location;

import java.util.List;

public interface LocationService {
    Response addLocation(AddLocationRequest addLocationRequest);
    long getLocationCount();
    Location findLocation(Long locationId);
    List<Location> findAllLocations();
    Response removeLocation(Long locationId);
    Response updateLocation(UpdateLocationRequest updateLocationRequest);
}
