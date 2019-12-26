export interface PaypalRequest {
  id: string;
  amount: number;
  currency: string;
  description: string;
  intent: string;
  paymentMethod: string;
  successUrl: string;
  failedUrl: string;
}
