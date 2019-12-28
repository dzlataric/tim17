import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PaypalPaymentComponent } from '../paypal-payment/paypal-payment.component'
import {PaypalSuccessComponent} from "../paypal-success/paypal-success.component";
import {PaypalFailedComponent} from "../paypal-failed/paypal-failed.component";


const routes: Routes = [
  {
    path: 'paypal',
    component: PaypalPaymentComponent,
  },
  {
    path: 'paypal-success',
    component: PaypalSuccessComponent,
  },
  {
    path: 'paypal-failed',
    component: PaypalFailedComponent,
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
