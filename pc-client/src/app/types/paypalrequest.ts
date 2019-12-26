export interface PaypalRequest {
  amount: number;
  currency: string;
  description: string;
  intent: string;
  paymentMethod: string;
}
