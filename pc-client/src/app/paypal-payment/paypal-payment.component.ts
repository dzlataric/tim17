import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient} from '@angular/common/http';

import {PaypalRequest} from '../types/paypalrequest';
import {PaypalResponse} from '../types/paypalresponse';

@Component({
  selector: 'app-paypal-payment',
  templateUrl: './paypal-payment.component.html',
  styleUrls: ['./paypal-payment.component.css']
})
export class PaypalPaymentComponent implements OnInit {

  paypalForm = new FormGroup({
    amount: new FormControl(''),
    currency: new FormControl(''),
    description: new FormControl(''),
    intent: new FormControl(''),
    paymentMethod: new FormControl('')
  });

  response: string;
  executeUrl: string;
  error: string;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.response = '';
  }

  formData() {
    let paypalRequest: PaypalRequest = {
      id: 'DJN',
      amount: this.paypalForm.value.amount,
      currency: this.paypalForm.value.currency,
      description: this.paypalForm.value.description,
      intent: this.paypalForm.value.intent,
      paymentMethod: this.paypalForm.value.paymentMethod,
      successUrl: 'http://localhost:4200/paypal-success',
      failedUrl: 'http://localhost:4200/paypal-failed'
    };
    this.createOrder(paypalRequest);
  }

  createOrder(paypalRequest: PaypalRequest) {
    this.http.post<PaypalResponse>('http://localhost:8080/paypal/create', paypalRequest).subscribe(
      (val) => {
        this.response = val.state;
        this.executeUrl = 'https://www.google.com';
        console.log("POST call successful value returned in body",
          val);
      },
      response => {
        this.error = 'Error with status: ' + response.status;
        console.log("POST call in error", response);
      },
      () => {
        console.log("The POST observable is now completed.");
      });
  }

  goToLink(){
    window.open(this.executeUrl, "_blank");
  }
}
