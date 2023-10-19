import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AddLocationRequest} from "../interfaces/add-location";
import {Observable} from "rxjs";
import {ApiResponse} from "../interfaces/api-response";
import {LocationUpdateRequest} from "../interfaces/location-update";
import {Location} from "../interfaces/location";

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private baseUrl: string = "http://localhost:8080/api/v1/location"
  constructor(private http: HttpClient) { }

  addLocation(addLocationRequest: AddLocationRequest): Observable<ApiResponse<Response>> {
    const url: string =   `${this.baseUrl}/addLocation`;
    return this.http.post<ApiResponse<Response>>(url, addLocationRequest)
  }

  removeLocation(locationId: number): Observable<ApiResponse<Response>> {
    const url: string = `${this.baseUrl}/remove/${locationId}`;
    return this.http.delete<ApiResponse<Response>>(url);
  }

  updateLocation(updateLocationRequest: LocationUpdateRequest): Observable<ApiResponse<Response>> {
    const url: string =   `${this.baseUrl}/updateLocation`;
    return this.http.put<ApiResponse<Response>>(url, updateLocationRequest)
  }

  viewAllLocation(): Observable<ApiResponse<Location[]>> {
    const url: string =   `${this.baseUrl}/viewAllLocations`;
    return this.http.get<ApiResponse<Location[]>>(url)
  }

}
