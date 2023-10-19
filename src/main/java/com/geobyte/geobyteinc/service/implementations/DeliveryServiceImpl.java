package com.geobyte.geobyteinc.service.implementations;

import com.geobyte.geobyteinc.data.dtos.Response;
import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;
import com.geobyte.geobyteinc.data.models.Delivery;
import com.geobyte.geobyteinc.data.models.Location;
import com.geobyte.geobyteinc.repository.DeliveryRepository;
import com.geobyte.geobyteinc.service.DeliveryService;
import com.geobyte.geobyteinc.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

  private final LocationService locationService;
  private final DeliveryRepository deliveryRepository;

  public DeliveryServiceImpl(LocationService locationService, DeliveryRepository deliveryRepository) {
    this.locationService = locationService;
    this.deliveryRepository = deliveryRepository;
  }


  /**
   * Add neighboring locations and calculate weight based on coordinates.
   *
   * @param deliverLocationRequest The request containing origin and destination location IDs.
   * @return A response indicating success and HTTP status.
   */
  @Override
  public Response addNeighboringLocations(DeliverLocationRequest deliverLocationRequest) {
    Location origin = locationService.findLocation(deliverLocationRequest.getOriginLocationId());
    Location destination = locationService.findLocation(deliverLocationRequest.getDestinationLocationId());
    Delivery deliver = new Delivery();

    double originLat = origin.getLatitude();
    double destinationLat = destination.getLatitude();
    double originLong = origin.getLongitude();
    double destinationLong = destination.getLongitude();
    double weight = deliver.computeWeight(originLat, destinationLat, originLong, destinationLong);

    deliver.setOriginLocation(origin);
    deliver.setDestinationLocation(destination);
    deliver.setWeight(weight);
    deliveryRepository.save(deliver);

    return Response.builder()
      .message("Delivery location added successfully")
      .status(HttpStatus.CREATED)
      .build();
  }


  /**
   * Retrieve all neighboring locations.
   *
   * @return A list of all neighboring locations.
   */
  @Override
  public List<Delivery> findAllNeighboringLocation() {
    return deliveryRepository.findAll();
  }


  /**
   * Retrieve all locations.
   *
   * @return A list of all locations.
   */
  @Override
  public List<Location> findAllLocations() {
    return locationService.findAllLocations();
  }
}
