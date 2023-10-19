package com.geobyte.geobyteinc.controller;

import com.geobyte.geobyteinc.data.dtos.request.AddLocationRequest;
import com.geobyte.geobyteinc.data.dtos.ApiResponse;
import com.geobyte.geobyteinc.data.dtos.request.UpdateLocationRequest;
import com.geobyte.geobyteinc.service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/addLocation")
    public ResponseEntity<ApiResponse> addLocation(@RequestBody AddLocationRequest addLocationRequest,
                                                   HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .path(httpServletRequest.getRequestURI())
                .data(locationService.addLocation(addLocationRequest))
                .isSuccessful(true)
                .build(), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{locationId}")
    public ResponseEntity<ApiResponse> removeLocation(@PathVariable Long locationId,
                                                      HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .path(httpServletRequest.getRequestURI())
                .data(locationService.removeLocation(locationId))
                .isSuccessful(true)
                .build(), HttpStatus.OK);
    }

    @PutMapping("/updateLocation")
    public ResponseEntity<ApiResponse> updateLocation(@RequestBody UpdateLocationRequest updateLocationRequest,
                                                   HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .path(httpServletRequest.getRequestURI())
                .data(locationService.updateLocation(updateLocationRequest))
                .isSuccessful(true)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/viewAllLocations")
    public ResponseEntity<ApiResponse> viewAllLocations() {
      return new ResponseEntity<>(ApiResponse.builder()
        .timeStamp(ZonedDateTime.now())
        .statusCode(HttpStatus.CREATED.value())
        .data(locationService.findAllLocations())
        .isSuccessful(true)
        .build(), HttpStatus.OK);
    }

}
