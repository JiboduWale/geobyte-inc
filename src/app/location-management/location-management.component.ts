import {Component, OnInit} from '@angular/core';
import {AddLocationRequest} from "../interfaces/add-location";
import {LocationService} from "../services/location.service";
import {Location} from "../interfaces/location";
import {LocationUpdateRequest} from "../interfaces/location-update";
import {tap} from "rxjs";

@Component({
  selector: 'app-location-management',
  templateUrl: './location-management.component.html',
  styleUrls: ['./location-management.component.css']
})
export class LocationManagementComponent implements OnInit{
  addLocationRequest: AddLocationRequest = {
    name: '',
    longitude: 0,
    latitude: 0
  };
  selectedLocation: Location = {
    id: 0,
    name: '',
    longitude: 0,
    latitude: 0,
  };

  updateLocationRequest: LocationUpdateRequest = {
    id: 0,
    name: '',
    longitude: 0,
    latitude: 0,
  };
  errorMessageAddLocation: string = '';
  locations: Location[] = [];
  errorMessageUpdateLocation: string = '';

  constructor(private locationService: LocationService) {}

  ngOnInit(): void {
    this.getLocations();
  }

  addLocation(): void {
    this.locationService.addLocation(this.addLocationRequest).pipe(
      tap({
        next: (response) => {
          this.getLocations();
        },
        error: () => {
          this.errorMessageAddLocation = 'Check your input data';
        }
      })
    ).subscribe();
  }

  deleteLocation(locationId: number): void {
    this.locationService.removeLocation(locationId).subscribe((response) => {
      // Handle success, e.g., show a success message
      this.getLocations();
    });
  }

  getLocations(): void {
    this.locationService.viewAllLocation().subscribe((response) => {
      if (response.data) {
        this.locations = response.data;
      }
    });
  }

  updateLocation(location: Location) {
    this.selectedLocation = { ...location };
    this.updateLocationRequest = { id: location.id, name: location.name, longitude: location.longitude, latitude: location.latitude };
  }

  updateSelectedLocation() {
    this.locationService.updateLocation(this.selectedLocation)
      .pipe(
        tap({
          next: (response) => {
            this.getLocations();
          },
          error: (error) => {
            if (error.status == 401) {
              this.errorMessageUpdateLocation = "Location update failed: you cannot update a location with the same name";
            }else {
              this.errorMessageUpdateLocation = "Unknown error occurred, please try again"
            }
          }
        })
      )
      .subscribe();
  }

}
