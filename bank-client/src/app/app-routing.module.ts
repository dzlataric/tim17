import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CardPaymentComponent } from './card-payment/card-payment.component';

const routes: Routes = [
  {
    path: 'card/payment/:transactionId',
    component: CardPaymentComponent,
  }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
