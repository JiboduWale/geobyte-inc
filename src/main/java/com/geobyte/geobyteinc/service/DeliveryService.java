package com.geobyte.geobyteinc.service;

import com.geobyte.geobyteinc.data.dtos.Response;
import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;
import com.geobyte.geobyteinc.data.models.Delivery;
import com.geobyte.geobyteinc.data.models.Location;

import java.util.List;


public interface DeliveryService {
  Response addNeighboringLocations(DeliverLocationRequest deliverLocationRequest);
  List<Delivery> findAllNeighboringLocation();
  List<Location> findAllLocations();
}
