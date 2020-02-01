import {AreasOfInterest} from './areasOfInterest';

export interface Author {
  id: number;
  firstName: string;
  lastName: string;
  country: string;
  city: string;
  emailAddress: string;
  mainAuthor: boolean;
  areasOfInterest: AreasOfInterest[];

}
