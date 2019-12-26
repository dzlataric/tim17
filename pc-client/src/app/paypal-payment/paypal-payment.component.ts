import{Component, OnInit}from '@angular/core';
import {FormControl, FormGroup}from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import {PaypalRequest}from '../types/paypalrequest';
import {catchError} from "rxjs/operators";

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

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  formData() {
    /*const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };*/
    let paypalRequest: PaypalRequest = {
      id: 'DJN',
      amount: this.paypalForm.value.amount,
      currency: this.paypalForm.value.currency,
      description: this.paypalForm.value.description,
      intent: this.paypalForm.value.intent,
      paymentMethod: this.paypalForm.value.paymentMethod,
      successUrl: 'http://localhost:4200/paypal-success',
      failedUrl: 'http://localhost:4200/paypal-failes'
    };
    console.log(paypalRequest);
    this.http.post<PaypalRequest>('http://localhost:8080/paypal/create', paypalRequest).subscribe(
      (val) => {
        console.log("POST call successful value returned in body",
          val);
      },
      response => {
        console.log("POST call in error", response);
      },
      () => {
        console.log("The POST observable is now completed.");
      });
  }

  private handleError() {
   console.log('erroe]r')
  }
}
