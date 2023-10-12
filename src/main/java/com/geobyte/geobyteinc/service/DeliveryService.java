package com.geobyte.geobyteinc.service;

import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;
import com.geobyte.geobyteinc.data.dtos.DeliveryResponse;


public interface DeliveryService {
    DeliveryResponse calculateOptimalRouteAndCost(DeliverLocationRequest deliverLocationRequest);
}
