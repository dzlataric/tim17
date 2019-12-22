import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PaypalPaymentComponent } from './paypal-payment/paypal-payment.component';

@NgModule({
  declarations: [
    AppComponent,
    PaypalPaymentComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
