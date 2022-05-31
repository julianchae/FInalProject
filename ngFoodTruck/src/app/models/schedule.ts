import { Location } from './location';
export class Schedule {

  id: number | null;
  arrival: string | null;
  departure: string |null;
  description: string | null;
  location: Location | null;

  constructor(
    id: number | null = 0,
    arrival: string | null = '',
    departure: string |null = '',
    description: string | null = '',
    location: Location | null = null

  )

  {
    this.id = id;
    this.arrival = arrival;
    this.departure= departure;
    this.description = description;
    this.location = location;
  }
}
