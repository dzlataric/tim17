import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { PaypalRequest } from '../types/paypalrequest';

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
    let paypalRequest: PaypalRequest = { amount: this.paypalForm.value.amount, currency: '', description: '', intent: '', paymentMethod: '' };
    console.log(this.paypalForm.value)
  }
}
