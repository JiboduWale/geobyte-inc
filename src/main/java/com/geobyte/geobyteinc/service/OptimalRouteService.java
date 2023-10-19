package com.geobyte.geobyteinc.service;

import com.geobyte.geobyteinc.data.dtos.DeliveryResponse;
import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;
import com.geobyte.geobyteinc.data.models.Location;

public interface OptimalRouteService {
  DeliveryResponse calculateOptimalRouteAndCost(DeliverLocationRequest deliverLocationRequest);
}
