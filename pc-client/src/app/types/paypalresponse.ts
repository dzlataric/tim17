import {PaypalLink} from './paypalLink';

export interface PaypalResponse {
  id: string;
  state: string;
  links: Array<PaypalLink>;
}
