import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiResponse} from "../interfaces/api-response";
import {DeliveryRequest} from "../interfaces/delivery-request";
import {Route} from "../interfaces/route";
import {Location} from "../interfaces/location";

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  private baseUrl: string = "http://localhost:8080/api/v1/delivery"
  constructor(private http: HttpClient) { }

  addNeighboringLocations(deliveryRequest: DeliveryRequest): Observable<ApiResponse<Response>> {
    const url: string =   `${this.baseUrl}/addNeighboringLocations`;
    return this.http.post<ApiResponse<Response>>(url, deliveryRequest)
  }

  calculateOptimalRouteAndCost(deliveryRequest: DeliveryRequest): Observable<ApiResponse<Route>> {
    const url: string = `${this.baseUrl}/calculateOptimalRouteAndCost`;
    return this.http.post<ApiResponse<Route>>(url, deliveryRequest);
  }

  getAllLocations(): Observable<ApiResponse<Location[]>> {
    const url: string =   `${this.baseUrl}/getAllLocations`;
    return this.http.get<ApiResponse<Location[]>>(url)
  }
}
