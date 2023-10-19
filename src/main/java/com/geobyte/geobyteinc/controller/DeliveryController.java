package com.geobyte.geobyteinc.controller;

import com.geobyte.geobyteinc.data.dtos.ApiResponse;
import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;
import com.geobyte.geobyteinc.service.DeliveryService;
import com.geobyte.geobyteinc.service.OptimalRouteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final OptimalRouteService optimalRouteService;

    public DeliveryController(DeliveryService deliveryService, OptimalRouteService optimalRouteService) {
        this.deliveryService = deliveryService;
      this.optimalRouteService = optimalRouteService;
    }

    @PostMapping("/addNeighboringLocations")
    public ResponseEntity<ApiResponse> addNeighboringLocations(@RequestBody DeliverLocationRequest deliverLocationRequest,
                                                                   HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .path(httpServletRequest.getRequestURI())
                .data(deliveryService.addNeighboringLocations(deliverLocationRequest))
                .isSuccessful(true)
                .build(), HttpStatus.CREATED);
    }

  @PostMapping("/calculateOptimalRouteAndCost")
  public ResponseEntity<ApiResponse> calculateOptimalRouteAndCost(@RequestBody DeliverLocationRequest deliverLocationRequest,
                                                            HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(ApiResponse.builder()
      .timeStamp(ZonedDateTime.now())
      .statusCode(HttpStatus.CREATED.value())
      .path(httpServletRequest.getRequestURI())
      .data(optimalRouteService.calculateOptimalRouteAndCost(deliverLocationRequest))
      .isSuccessful(true)
      .build(), HttpStatus.CREATED);
  }

  @GetMapping("/getAllLocations")
  public ResponseEntity<ApiResponse> getAllLocations() {
    return new ResponseEntity<>(ApiResponse.builder()
      .timeStamp(ZonedDateTime.now())
      .statusCode(HttpStatus.CREATED.value())
      .data(deliveryService.findAllLocations())
      .isSuccessful(true)
      .build(), HttpStatus.OK);
  }
}
