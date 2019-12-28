import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient} from '@angular/common/http';

import {OrderMagazineRequest} from '../types/orderMagazineRequest';
import {OrderMagazineResponse} from '../types/orderMagazineResponse';

@Component({
  selector: 'app-order-magazine',
  templateUrl: './order-magazine.component.html',
  styleUrls: ['./order-magazine.component.css']
})
export class OrderMagazineComponent implements OnInit {

  orderMagazineForm = new FormGroup({
    magazine: new FormControl('')
  });

  // load magazines from api this is just for testing
  magazines = [ {issn:'12345678', membership_fee_type:'READER_FEE', title:'magazine1', price:'1'},
    {issn:'12345679', membership_fee_type:'READER_FEE', title:'magazine2', price:'1'},
    {issn:'12345689', membership_fee_type:'READER_FEE', title:'magazine3', price:'1'},
    {issn:'12345789', membership_fee_type:'READER_FEE', title:'magazine4', price:'1'}];

  response: string;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.response = '';
  }

  formData() {
    let orderMagazineRequest: OrderMagazineRequest = {
      userId: '123', //add id
      magazineId: this.orderMagazineForm.value.magazine.issn,
      price: this.orderMagazineForm.value.magazine.price
    };
    this.createOrder(orderMagazineRequest);
  }

  createOrder(orderMagazineRequest: OrderMagazineRequest) {
    this.http.post<OrderMagazineResponse>('https://localhost:8082/order/place', orderMagazineRequest).subscribe(
      (val) => {
        this.response = val.title;
        console.log("POST call successful value returned in body", val);
      });
  }

}
