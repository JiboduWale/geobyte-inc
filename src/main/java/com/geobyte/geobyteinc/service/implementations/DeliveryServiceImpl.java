package com.geobyte.geobyteinc.service.implementations;

import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;
import com.geobyte.geobyteinc.data.dtos.DeliveryResponse;
import com.geobyte.geobyteinc.data.models.Delivery;
import com.geobyte.geobyteinc.data.models.Location;
import com.geobyte.geobyteinc.data.models.NeighboringLocations;
import com.geobyte.geobyteinc.repository.DeliveryRepository;
import com.geobyte.geobyteinc.repository.NeighboringLocationRepository;
import com.geobyte.geobyteinc.service.DeliveryService;
import com.geobyte.geobyteinc.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final LocationService locationService;
    private final DeliveryRepository deliveryRepository;
    private final NeighboringLocationRepository neighboringLocationRepository;

    public DeliveryServiceImpl(LocationService locationService, DeliveryRepository deliveryRepository, NeighboringLocationRepository neighboringLocationRepository) {
        this.locationService = locationService;
        this.deliveryRepository = deliveryRepository;
        this.neighboringLocationRepository = neighboringLocationRepository;
    }

    /**
     * Calculate the optimal route and cost for a delivery.
     *
     * @param deliverLocationRequest The request containing origin and destination location IDs.
     * @return DeliveryResponse with the optimal route and cost.
     */
    @Override
    public DeliveryResponse calculateOptimalRouteAndCost(DeliverLocationRequest deliverLocationRequest) {
        Location originLocation = locationService.findLocation(deliverLocationRequest.getOriginLocationId());
        Location destinationLocation = locationService.findLocation(deliverLocationRequest.getDestinationLocationId());

        List<Location> allLocations = locationService.findAllLocations();


        // Calculate the shortest path using Dijkstra's algorithm
        Map<Location, Double> distanceMap = calculateShortestPath(originLocation, destinationLocation, allLocations);

        List<Location> optimalRoute = extractOptimalRoute(destinationLocation, distanceMap);
        double totalCost = calculateTotalCost(originLocation, optimalRoute, distanceMap);

        Delivery delivery = createDelivery(originLocation, destinationLocation, optimalRoute, totalCost);

        DeliveryResponse response = new DeliveryResponse();
        response.setOptimalRoute(delivery.getOptimalRoute());
        response.setCost(delivery.getTotalCost());
        response.setMessage("Delivery optimal route and cost created successfully");
        response.setStatus(HttpStatus.CREATED);

        return response;
    }

    // Private helper methods

    private Delivery createDelivery(Location originLocation, Location destinationLocation, List<Location> optimalRoute, double totalCost) {
        Delivery delivery = new Delivery();
        delivery.setOriginLocation(originLocation);
        delivery.setDestinationLocation(destinationLocation);
        delivery.setOptimalRoute(optimalRoute);
        delivery.setLocalDateTime(LocalDateTime.now());
        delivery.setTotalCost(totalCost);
        return deliveryRepository.save(delivery);
    }

    private List<Location> extractOptimalRoute(Location destinationLocation, Map<Location, Double> distanceMap) {
        List<Location> optimalRoute = new ArrayList<>();
        Location currentLocation = destinationLocation;

        while (currentLocation != null) {
            optimalRoute.add(currentLocation);
            currentLocation = distanceMap.get(currentLocation) == 0.0 ? null : findPredecessor(currentLocation, distanceMap);
        }

        Collections.reverse(optimalRoute);
        return optimalRoute;
    }

    private Location findPredecessor(Location location, Map<Location, Double> distanceMap) {
        Location predecessor = null;
        double minDistance = Double.POSITIVE_INFINITY;

        List<NeighboringLocations> neighboringLocations = neighboringLocations(location);

        for (NeighboringLocations neighbor : neighboringLocations) {
            Location neighboringLocation = locationService.findLocation(neighbor.getId());
            double neighborDistance = distanceMap.get(neighboringLocation);
            if (neighborDistance < minDistance) {
                minDistance = neighborDistance;
                predecessor = neighboringLocation;
            }
        }

        return predecessor;
    }

    private List<NeighboringLocations> neighboringLocations(Location location) {
        List<NeighboringLocations> locationNeighbors = new ArrayList<>();
        List<NeighboringLocations> neighboringLocations = neighboringLocationRepository.findAll();

        for (NeighboringLocations neighboringLocation : neighboringLocations) {
            Long neighboringLocationId = neighboringLocation.getId();

            // Check if the neighboring location is not the same as the current location
            if (!neighboringLocationId.equals(location.getId())) {
                // Add the neighboring location to the currentLocationNeighbors list
                locationNeighbors.add(neighboringLocation);
            }
        }
        return locationNeighbors;
    }

    private double calculateTotalCost(Location originLocation, List<Location> optimalRoute, Map<Location, Double> distanceMap) {
        double clearingCost = 0.0;

        for (Location location : optimalRoute) {
            if (!location.equals(originLocation)) {
                double randomClearingCost = 25 + Math.random() * (100 - 25 + 1);
                clearingCost += randomClearingCost;
            }
        }

        double costPerKilometer = 1.0;
        double costPerKilometerComponent = costPerKilometer * distanceMap.get(optimalRoute.get(optimalRoute.size() - 1));

        return costPerKilometerComponent + clearingCost;
    }

    private Map<Location, Double> calculateShortestPath(Location source, Location destination, List<Location> locations) {
        Map<Location, Double> distanceMap = new HashMap<>();
        Map<Location, Location> predecessorMap = new HashMap<>();
        Set<Location> visited = new HashSet<>();

        for (Location location : locations) {
            distanceMap.put(location, Double.POSITIVE_INFINITY);
            predecessorMap.put(location, null);
        }

        distanceMap.put(source, 0.0);

        PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparingDouble(distanceMap::get));
        queue.add(source);

        while (!queue.isEmpty()) {
            Location currentLocation = queue.poll();

            if (currentLocation.equals(destination)) {
                break;
            }

            if (!visited.contains(currentLocation)) {
                visited.add(currentLocation);

                List<NeighboringLocations> currentLocationNeighbors = neighboringLocations(currentLocation);

                for (NeighboringLocations neighbor : currentLocationNeighbors) {
                    Location location = locationService.findLocation(neighbor.getId());
                    if (!visited.contains(location)) {
                       
                        double tentativeDistance = distanceMap.get(currentLocation) + currentLocation.getDistanceTo(location);

                        if (tentativeDistance < distanceMap.get(location)) {
                            distanceMap.put(location, tentativeDistance);
                            predecessorMap.put(location, currentLocation);
                            queue.add(location);
                        }
                    }
                }
            }
        }

        return distanceMap;
    }
}
