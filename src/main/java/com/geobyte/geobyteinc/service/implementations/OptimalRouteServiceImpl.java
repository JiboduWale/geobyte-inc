package com.geobyte.geobyteinc.service.implementations;

import com.geobyte.geobyteinc.data.dtos.DeliveryResponse;
import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;
import com.geobyte.geobyteinc.data.models.Delivery;
import com.geobyte.geobyteinc.data.models.Graph;
import com.geobyte.geobyteinc.data.models.Location;
import com.geobyte.geobyteinc.service.DeliveryService;
import com.geobyte.geobyteinc.service.LocationService;
import com.geobyte.geobyteinc.service.OptimalRouteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptimalRouteServiceImpl implements OptimalRouteService {

  private final LocationService locationService;
  private final DeliveryService deliveryService;

  public OptimalRouteServiceImpl(LocationService locationService, DeliveryService deliveryService) {
    this.locationService = locationService;
    this.deliveryService = deliveryService;
  }

  /**
   * Calculate the optimal route and cost for delivering between two locations.
   *
   * @param deliverLocationRequest The request containing origin and destination location IDs.
   * @return A response containing the optimal route, total cost, and HTTP status.
   */
  @Override
  public DeliveryResponse calculateOptimalRouteAndCost(DeliverLocationRequest deliverLocationRequest) {
    List<Location> allLocations = locationService.findAllLocations();
    DeliveryResponse deliveryResponse = new DeliveryResponse();

    if (locationService.getLocationCount() != 3) {
      throw new IllegalStateException("There has to be a minimum of 3 locations before delivery can start");
    }else {
      List<Delivery> allNeighboringLocations = deliveryService.findAllNeighboringLocation();
      Graph graph = new Graph(allLocations, allNeighboringLocations);

      Location originLocation = locationService.findLocation(deliverLocationRequest.getOriginLocationId());
      Location destinationLocation = locationService.findLocation(deliverLocationRequest.getDestinationLocationId());

      DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
      dijkstraAlgorithm.execute(originLocation);
      List<Location> optimalRoute = dijkstraAlgorithm.getShortestPath(destinationLocation);
      double totalCost = dijkstraAlgorithm.calculateTotalCost(originLocation, optimalRoute);


      deliveryResponse.setMessage("Total cost and optimal route calculated successfully");
      deliveryResponse.setOptimalRoute(optimalRoute);
      deliveryResponse.setCost(totalCost);
      deliveryResponse.setStatus(HttpStatus.CREATED);
    }

    return deliveryResponse;
  }


}
