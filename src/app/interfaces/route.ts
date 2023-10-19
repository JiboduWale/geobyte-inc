import { Location } from './location';

export interface Route {
  message: string;
  status: number
  optimalRoute: Location[];
  cost: number;
}
