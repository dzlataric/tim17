import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ExecutePayment} from "../types/executepayment";
import {PaypalResponse} from "../types/paypalresponse";
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-paypal-success',
  templateUrl: './paypal-success.component.html',
  styleUrls: ['./paypal-success.component.css']
})
export class PaypalSuccessComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) {
  }

  success: boolean;
  error: boolean;
  errorStatus: string;
  token: string;
  paymentId: string;
  payerId: string;

  ngOnInit() {
    this.success = true;
    this.activatedRoute.queryParams.subscribe(params => {
      console.log(params);
      this.token = params['token'];
      this.paymentId = params['paymentId'];
      this.payerId = params['PayerID'];
    });
    console.log(this.token);
  }

  execute() {
    let executePayment: ExecutePayment = {
      payerId: this.payerId,
      paymentId: this.paymentId,
      token: this.token
    };
    this.executePayment(executePayment);
  }

  executePayment(executePayment: ExecutePayment) {
    console.log(JSON.stringify(executePayment));
    this.success = false;
    this.http.post<String>('http://localhost:8080/paypal/execute', executePayment).subscribe(
      (val) => {
        this.error = false;
        console.log("Execute payment call successful value returned in body", val);
      },
      response => {
        this.errorStatus = response.status;
        this.error = true;
        console.log("Execute payment call in error", response);
      },
      () => {
        console.log("The Execute payment observable is now completed.");
        window.open('localhost:4200/paypal', "_self");
      });
  }
}
