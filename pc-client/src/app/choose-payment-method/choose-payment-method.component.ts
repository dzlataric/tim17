import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {OrderRequest} from '../types/orderRequest';
import {OrderResponse} from '../types/orderResponse';

@Component({
  selector: 'app-choose-payment-method',
  templateUrl: './choose-payment-method.component.html',
  styleUrls: ['./choose-payment-method.component.css']
})
export class ChoosePaymentMethodComponent implements OnInit {

  transactionId: string;

  constructor(private router: Router, private http: HttpClient) {
  }

  ngOnInit() {
  }

  testOrderRequest() {
    console.log("this is test request");
    let orderRequest: OrderRequest = {
      id: "123",
      merchantId: "3b0d80ab-e2b7-43de-b84f-7bd0ecac4231",
      amount: 123,
      currency: "RSD",
      merchantTimestamp: "12.12.2019 13:43"
    };

    this.http.post<OrderResponse>("https://localhost:8083/card/paymentUrl", orderRequest).subscribe(
      (val) => {
        console.log("POST call successful value returned in body", val);
        window.open(val.paymentUrl+"/"+val.id, "_self");
      });
  }

  redirect(state: string) {
    this.router.navigate(['./' + state]);
  }

}
