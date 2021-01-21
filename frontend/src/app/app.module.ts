import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { ProductDetailsComponent } from './components/products/product-details/product-details.component';
import { CartComponent } from './components/products/cart/cart.component';
import { CartSummaryComponent } from './components/products/checkout/cart-summary/cart-summary.component';
import { ShippingDetailsComponent } from './components/products/checkout/shipping-details/shipping-details.component';
import { PaymentComponent } from './components/products/checkout/payment/payment.component';
import { OrderHistoryComponent } from './components/products/orders/order-history/order-history.component';
import { DeliveryHistoryComponent } from './components/products/orders/delivery-history/delivery-history.component';
import { AddProductComponent } from './components/admin/add-product/add-product.component';
import { ViewProductComponent } from './components/admin/view-product/view-product.component';
import { ProductsComponent } from './components/products/products.component';
import { OrdersComponent } from './components/products/orders/orders.component';
import { CheckoutComponent } from './components/products/checkout/checkout.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProductDetailsComponent,
    CartComponent,
    CartSummaryComponent,
    ShippingDetailsComponent,
    PaymentComponent,
    OrderHistoryComponent,
    DeliveryHistoryComponent,
    AddProductComponent,
    ViewProductComponent,
    ProductsComponent,
    OrdersComponent,
    CheckoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
