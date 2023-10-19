package com.geobyte.geobyteinc.service.implementations;


import com.geobyte.geobyteinc.data.models.Delivery;
import com.geobyte.geobyteinc.data.models.Graph;
import com.geobyte.geobyteinc.data.models.Location;


import java.util.*;

public class DijkstraAlgorithm  {

  private final List<Location> nodes; // List of locations
  private final List<Delivery> edges; // List of deliveries
  private Set<Location> visited; // Set of visited locations
  private Set<Location> notVisited; // Set of unvisited locations
  private Map<Location, Location> predecessors; // Mapping of predecessors for each location
  private Map<Location, Double> distanceMap; // Mapping of distances for each location

  public DijkstraAlgorithm(Graph graph) {
    this.nodes = new ArrayList<>(graph.getLocations());
    this.edges = new ArrayList<>(graph.getNeighboringLocations());
  }


  /**
   * Execute Dijkstra's algorithm from the specified origin location.
   *
   * @param origin The origin location for the shortest path calculation.
   */
  public void execute(Location origin) {

    visited = new HashSet<>();
    notVisited = new HashSet<>();
    distanceMap = new HashMap<>();
    predecessors = new HashMap<>();
    distanceMap.put(origin, 0.0);
    notVisited.add(origin);
    while (notVisited.size() > 0) {
      Location node = getMinimum(notVisited);
      visited.add(node);
      notVisited.remove(node);
      updateLocationDistance(node);
    }
  }


  /**
   * Get the shortest path from the origin location to the target location.
   *
   * @param target The target location for which to find the shortest path.
   * @return A list of locations representing the shortest path.
   */
  public List<Location> getShortestPath(Location target) {
    List<Location> path = new ArrayList<>();
    Location step = target;
    // check if a path exists
    if (predecessors.get(step) == null) {
      return null;
    }
    path.add(step);
    while (predecessors.get(step) != null) {
      step = predecessors.get(step);
      path.add(step);
    }
    Collections.reverse(path);
    return path;
  }


  /**
   * Calculate the total cost of the optimal route.
   *
   * @param originLocation The origin location.
   * @param optimalRoute    The optimal route (list of locations).
   * @return The total cost of the optimal route.
   */
  public double calculateTotalCost(Location originLocation, List<Location> optimalRoute) {
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

  // Private helper methods
  private void updateLocationDistance(Location node) {
    List<Location> adjacentNodes = getAdjacentNodes(node);
    for (Location target : adjacentNodes) {
      if (getShortestDistance(target) > getShortestDistance(node)
        + getWeight(node, target)) {
        distanceMap.put(target, getShortestDistance(node)
          + getWeight(node, target));
        predecessors.put(target, node);
        notVisited.add(target);
      }
    }
  }

  private double getWeight(Location node, Location target) {
    for (Delivery edge : edges) {
      if (edge.getOriginLocation().equals(node)
        && edge.getDestinationLocation().equals(target)) {
        return edge.getWeight();
      }
    }
    throw new RuntimeException("Should not happen");
  }

  private List<Location> getAdjacentNodes(Location node) {
    List<Location> neighbors = new ArrayList<>();
    for (Delivery edge : edges) {
      if (edge.getOriginLocation().equals(node)
        && !visited(edge.getDestinationLocation())) {
        neighbors.add(edge.getDestinationLocation());
      }
    }
    return neighbors;
  }

  private boolean visited(Location destinationLocation) {
    return visited.contains(destinationLocation);
  }

  private Location getMinimum(Set<Location> locations) {
    Location minimum = null;
    for (Location location : locations) {
      if (minimum == null) {
        minimum = location;
      } else {
        if (getShortestDistance(location) < getShortestDistance(minimum)) {
          minimum = location;
        }
      }
    }
    return minimum;
  }

  private Double getShortestDistance(Location destination) {
    Double distance = distanceMap.get(destination);
    if (distance == null) {
      return (double) Integer.MAX_VALUE;
    } else {
      return distance;
    }
  }
}
