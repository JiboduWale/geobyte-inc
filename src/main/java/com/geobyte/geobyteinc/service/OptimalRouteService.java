package com.geobyte.geobyteinc.service;

import com.geobyte.geobyteinc.data.dtos.DeliveryResponse;
import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;


public interface OptimalRouteService {
  DeliveryResponse calculateOptimalRouteAndCost(DeliverLocationRequest deliverLocationRequest);
}
