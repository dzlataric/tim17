import {AreaOfScience} from './areaOfScience';

export interface Editor {

   id: number;
   firstName: string;
   lastName: string;
   isChief: boolean;
   areaOfScience: AreaOfScience;
}
