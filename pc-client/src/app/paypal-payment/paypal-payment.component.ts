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
  confirmUrl: string;
  executeUrl: string;
  error: boolean;
  isVisible: boolean = false;
  success: boolean = false;
  statusCode: string;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
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
    console.log(paypalRequest);
    this.http.post<PaypalResponse>('https://localhost:8080/paypal/create', paypalRequest).subscribe(
      (val) => {
        this.response = val.state;
        this.isVisible = true;
        this.error = false;
        this.success = true;
        val.links.forEach(link => {
          if (link.type === 'execute') {
            this.executeUrl = link.url;
          } else if (link.type === 'approval_url') {
            this.confirmUrl = link.url;
          }
        });
        console.log("POST call successful value returned in body", val);
      },
      response => {
        this.isVisible = false;
        this.statusCode = response.status;
        this.error = true;
        this.success = false;
        console.log("POST call in error", response);
      },
      () => {
        console.log("The POST observable is now completed.");
      });
  }

  confirm() {
    window.open(this.confirmUrl, "_self");
    localStorage.setItem('executeUrl', this.executeUrl);
  }
}
