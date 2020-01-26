import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
//import {AppSettings} from '../app-settings/app-settings';
import { ActivatedRoute, Params } from "@angular/router";
import { DatePipe } from '@angular/common';

import { CardDetailsRequest } from 'src/types/cardDetailsRequest';
import { CardDetailsResponse } from 'src/types/cardDetailsResponse';

@Component({
  selector: 'app-card-payment',
  templateUrl: './card-payment.component.html',
  styleUrls: ['./card-payment.component.css']
})
export class CardPaymentComponent implements OnInit {

  cardInfoForm = new FormGroup({
    pan: new FormControl(''),
    chn: new FormControl(''),
    cvv: new FormControl(''),
    validThru: new FormControl('')
  });

  response: string;
  confirmUrl: string;
  error: boolean;
  isVisible: boolean = false;
  success: boolean = false;
  statusCode: string;

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
  }

  formData() {
    let tId;
    this.route.params.subscribe((data) => { tId = data.transactionId; });
    let cardDetailsRequest: CardDetailsRequest = {
      transactionId: tId,
      pan: this.cardInfoForm.value.pan,
      chn: this.cardInfoForm.value.chn,
      cvv: this.cardInfoForm.value.cvv,
      validThru: new DatePipe("en-US").transform(this.cardInfoForm.value.validThru, 'dd/MM/yyyy')
    };
    this.createOrder(cardDetailsRequest);
  }

  createOrder(request: CardDetailsRequest) {
    console.log(request);
    this.http.post<CardDetailsResponse>("https://localhost:8080/card/payment", request).subscribe(
      (val) => {
        // this.response = val.status;
        // this.isVisible = true;
        // this.error = false;
        // this.success = true;
        // this.confirmUrl = val.payment_url;
        console.log("POST call successful value returned in body", val);
      },
      response => {
        // this.isVisible = false;
        // this.statusCode = response.status;
        // this.error = true;
        // this.success = false;
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

