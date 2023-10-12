package com.geobyte.geobyteinc.controller;

import com.geobyte.geobyteinc.data.dtos.ApiResponse;
import com.geobyte.geobyteinc.data.dtos.request.DeliverLocationRequest;
import com.geobyte.geobyteinc.service.DeliveryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
@RestController
@RequestMapping("api/v1/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/calculateOptimalRouteAndCost")
    public ResponseEntity<ApiResponse> calculateOptimalRouteAndCost(@RequestBody DeliverLocationRequest deliverLocationRequest,
                                                                   HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .path(httpServletRequest.getRequestURI())
                .data(deliveryService.calculateOptimalRouteAndCost(deliverLocationRequest))
                .isSuccessful(true)
                .build(), HttpStatus.CREATED);
    }
}
