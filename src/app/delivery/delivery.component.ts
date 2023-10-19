import { Component, OnInit } from '@angular/core';
import { DeliveryService } from '../services/delivery.service';
import { Location } from '../interfaces/location';
import {Route} from '../interfaces/route';
import {catchError, of, tap} from "rxjs";

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.css']
})
export class DeliveryComponent implements OnInit {
  deliveryRequest = {
    originLocationId: 0,
    destinationLocationId: 0
  };
  locations: Location[] = [];
  route: Route = {
    message: '',
    status: 0,
    optimalRoute: [],
    cost: 0
  };

  constructor(private deliveryService: DeliveryService) {}

  ngOnInit(): void {
    this.getLocations();
  }

  getLocations(): void {
    this.deliveryService.getAllLocations().subscribe((response) => {
      if (response.data) {
        this.locations = response.data;
      }
    });
  }

  addNeighboringLocations(): void {
    // Implement the logic to add neighboring locations
    this.deliveryService.addNeighboringLocations(this.deliveryRequest).subscribe((response) => {
    });
  }

  calculateOptimalRouteAndCost() {
    this.deliveryService.calculateOptimalRouteAndCost(this.deliveryRequest)
      .pipe(
        tap((response) => {
          // Handle the response, which contains the calculated route
          this.route = response.data; // Update the route property
          console.log('Optimal route calculated successfully', this.route);
        }),
        catchError((error) => {
          console.error('Error calculating optimal route', error);
          return of(null);
        })
      )
      .subscribe();
  }
}
