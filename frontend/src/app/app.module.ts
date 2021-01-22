/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-21 12:46:55
 * @modify date 2021-01-21 12:46:55
 * @desc Root module
 */
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
import { AdminComponent } from './components/admin/admin.component';
import { FooterComponent } from './shared/footer/footer.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';
import { NavigationComponent } from './shared/navigation/navigation.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { httpInterceptors } from './providers';
import { ChangePasswordComponent } from './components/auth/change-password/change-password.component';
import { RequestQuestionComponent } from './components/auth/request-question/request-question.component';
import { AboutUsComponent } from './components/products/about-us/about-us.component';

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
    CheckoutComponent,
    AdminComponent,
    FooterComponent,
    NotFoundComponent,
    NavigationComponent,
    ChangePasswordComponent,
    RequestQuestionComponent,
    AboutUsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    httpInterceptors,
    JwtHelperService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
