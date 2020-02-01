import {MembershipFeeType} from './membershipFeeType';
import {Editor} from './editor';
import {AreaOfScience} from './areaOfScience';
import {PaymentType} from './paymentType';



export interface Magazine {

   id: number;
   title: string;
   issn: number;
   membershipFeeType: MembershipFeeType;
   editors: Editor[];
   areasOfScience: AreaOfScience[];
  paymentTypes: PaymentType[];

}
