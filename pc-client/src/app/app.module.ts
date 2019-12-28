import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PaypalPaymentComponent } from './paypal-payment/paypal-payment.component';
import { PaypalSuccessComponent } from './paypal-success/paypal-success.component';
import { PaypalFailedComponent } from './paypal-failed/paypal-failed.component'
import { OrderMagazineComponent } from './order-magazine/order-magazine.component';
import { ChoosePaymentMethodComponent } from './choose-payment-method/choose-payment-method.component'

@NgModule({
  declarations: [
    AppComponent,
    PaypalPaymentComponent,
    PaypalSuccessComponent,
    PaypalFailedComponent
    OrderMagazineComponent,
    ChoosePaymentMethodComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
