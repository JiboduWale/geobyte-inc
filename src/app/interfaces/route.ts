import { Location } from './add-location';

export interface Route {
  id: string;
  origin: Location;
  destination: Location;
  optimalRoute: Location[]; // List of locations in the route
  cost: number;
}
