export interface OrderRequest {
  id: string;
  merchantId: string;
  amount: number;
  currency: string;
  merchantTimestamp: string;
}
