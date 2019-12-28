import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PaypalPaymentComponent } from '../paypal-payment/paypal-payment.component';
import {PaypalSuccessComponent} from "../paypal-success/paypal-success.component";
import {OrderMagazineComponent} from "../order-magazine/order-magazine.component";
import { ChoosePaymentMethodComponent } from '../choose-payment-method/choose-payment-method.component'

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
    path: 'order-magazine',
    component: OrderMagazineComponent,
  },
  {
    path: 'choose-payment',
    component: ChoosePaymentMethodComponent,
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
