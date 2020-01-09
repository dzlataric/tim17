import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {AppSettings} from '../app-settings/app-settings';

import {CryptoRequest} from '../types/cryptoRequest';
import {CryptoResponse} from '../types/CryptoResponse';

@Component({
  selector: 'app-crypto-payment',
  templateUrl: './crypto-payment.component.html',
  styleUrls: ['./crypto-payment.component.css']
})
export class CryptoPaymentComponent implements OnInit {

  cryptoForm = new FormGroup({
    priceAmount: new FormControl(''),
    priceCurrency: new FormControl('')
  });

  response: string;
  confirmUrl: string;
  error: boolean;
  isVisible: boolean = false;
  success: boolean = false;
  statusCode: string;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  formData() {
    let cryptoRequest: CryptoRequest = {
      priceAmount: this.cryptoForm.value.priceAmount,
      priceCurrency: this.cryptoForm.value.priceCurrency
    };
    this.createOrder(cryptoRequest);
  }

  createOrder(cryptoRequest: CryptoRequest) {
    console.log(cryptoRequest);
    this.http.post<CryptoResponse>(AppSettings.CRYPTO_API_CREATE, cryptoRequest).subscribe(
      (val) => {
        this.response = val.status;
        this.isVisible = true;
        this.error = false;
        this.success = true;
        this.confirmUrl = val.payment_url;
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
  }
}
