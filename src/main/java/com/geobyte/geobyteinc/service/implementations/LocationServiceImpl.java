package com.geobyte.geobyteinc.service.implementations;

import com.geobyte.geobyteinc.data.dtos.request.AddLocationRequest;
import com.geobyte.geobyteinc.data.dtos.Response;
import com.geobyte.geobyteinc.data.dtos.request.UpdateLocationRequest;
import com.geobyte.geobyteinc.data.models.Location;
import com.geobyte.geobyteinc.repository.LocationRepository;
import com.geobyte.geobyteinc.security.LocationAlreadyExistException;
import com.geobyte.geobyteinc.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of LocationService providing location management functionality.
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    /**
     * Add a new location and its neighboring locations.
     *
     * @param addLocationRequest The request containing location details.
     * @return Response indicating the result of the operation.
     */
    @Transactional
    @Override
    public Response addLocation(AddLocationRequest addLocationRequest) {
        validateLocationInputData(addLocationRequest);

        Location location = createLocationFromRequest(addLocationRequest);

        try {
            locationRepository.save(location);

            return createSuccessResponse("Location saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return createErrorResponse();
        }
    }

    /**
     * Find a location by its ID.
     *
     * @param locationId The ID of the location to find.
     * @return The found location.
     * @throws LocationAlreadyExistException If the location is not found.
     */
    @Override
    public Location findLocation(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationAlreadyExistException("Location not found"));
    }

    /**
     * Get a list of all locations.
     *
     * @return List of all locations.
     */
    @Override
    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    /**
     * Remove a location by its ID.
     *
     * @param locationId The ID of the location to remove.
     * @return Response indicating the result of the operation.
     */
    @Override
    public Response removeLocation(Long locationId) {
        Location location = findLocation(locationId);
        locationRepository.delete(location);

        return createSuccessResponse("Location deleted successfully", HttpStatus.OK);
    }

    /**
     * Update the details of a location.
     *
     * @param updateLocationRequest The request containing updated location details.
     * @return Response indicating the result of the operation.
     */
    @Override
    public Response updateLocation(UpdateLocationRequest updateLocationRequest) {

      Location location = validateUpdateLocationInputData(updateLocationRequest);

      updateLocationDetails(location, updateLocationRequest);
        locationRepository.save(location);

        return createSuccessResponse("Location updated successfully", HttpStatus.OK);
    }


  /**
     * Get the total count of locations.
     *
     * @return The count of locations.
     */
    @Override
    public long getLocationCount() {
        return locationRepository.count();
    }



  // Private helper methods

    private void  validateLocationInputData(AddLocationRequest addLocationRequest) {
      if (addLocationRequest == null || addLocationRequest.getName().equals("")) {
        throw new IllegalArgumentException("Invalid input data");
      }

      String newName = addLocationRequest.getName();
      List<Location> locations = locationRepository.findAll();

      for (Location location: locations) {

        if (location.getName().equals(newName)) {
          throw new IllegalArgumentException("Location name already exist");
        }
      }
    }

  private Location validateUpdateLocationInputData(UpdateLocationRequest updateLocationRequest) {
    if (updateLocationRequest == null || updateLocationRequest.getName().equals("")) {
      throw  new IllegalArgumentException("Invalid input data");
    }

    Location location = findLocation(updateLocationRequest.getId());

    if (location.getName().equals(updateLocationRequest.getName())) {
      throw new LocationAlreadyExistException("You cannot update a location with the same name");
    }
    return location;
  }

    private Location createLocationFromRequest(AddLocationRequest addLocationRequest) {
        Location location = new Location();
        location.setName(addLocationRequest.getName().toLowerCase());
        location.setLongitude(addLocationRequest.getLongitude());
        location.setLatitude(addLocationRequest.getLatitude());
        return location;
    }


    private Response createSuccessResponse(String message, HttpStatus status) {
        return Response.builder()
                .message(message)
                .status(status)
                .build();
    }

    private Response createErrorResponse() {
        return Response.builder()
                .message("Error saving location")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    private void updateLocationDetails(Location location, UpdateLocationRequest updateLocationRequest) {
        location.setName(updateLocationRequest.getName());
        location.setLatitude(updateLocationRequest.getLatitude());
        location.setLongitude(updateLocationRequest.getLongitude());
    }
}
