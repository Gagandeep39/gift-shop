/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-21 12:47:19
 * @modify date 2021-01-21 12:47:19
 * @desc Routing module
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProductComponent } from './components/admin/add-product/add-product.component';
import { AdminComponent } from './components/admin/admin.component';
import { UpdateProductComponent } from './components/admin/update-product/update-product.component';
import { ViewProductComponent } from './components/admin/view-product/view-product.component';
import { ChangePasswordComponent } from './components/auth/change-password/change-password.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { RequestQuestionComponent } from './components/auth/request-question/request-question.component';
import { AboutUsComponent } from './components/products/about-us/about-us.component';
import { CartComponent } from './components/products/cart/cart.component';
import { CartSummaryComponent } from './components/products/checkout/cart-summary/cart-summary.component';
import { CheckoutComponent } from './components/products/checkout/checkout.component';
import { PaymentComponent } from './components/products/checkout/payment/payment.component';
import { ShippingDetailsComponent } from './components/products/checkout/shipping-details/shipping-details.component';
import { DeliveryHistoryComponent } from './components/products/orders/delivery-history/delivery-history.component';
import { OrderHistoryComponent } from './components/products/orders/order-history/order-history.component';
import { ProductDetailsComponent } from './components/products/product-details/product-details.component';
import { ProductListComponent } from './components/products/product-list/product-list.component';
import { ProductsComponent } from './components/products/products.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'forgotpassword', component: RequestQuestionComponent },
  { path: 'changepassword', component: ChangePasswordComponent },
  {
    path: 'products',
    component: ProductsComponent,
    children: [
      { path: '', component: ProductListComponent },
      { path: 'cart', component: CartComponent },
      { path: 'orders', component: OrderHistoryComponent },
      { path: 'about', component: AboutUsComponent },
      { path: 'delivery/:orderId', component: DeliveryHistoryComponent },
      {
        path: 'checkout',
        component: CheckoutComponent,
        children: [
          { path: 'summary', component: CartSummaryComponent },
          { path: 'shipping', component: ShippingDetailsComponent },
          { path: 'payment', component: PaymentComponent },
        ],
      },
      { path: ':productId', component: ProductDetailsComponent },
    ],
  },
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: 'add', component: AddProductComponent },
      { path: 'update', component: UpdateProductComponent },
      { path: 'view', component: ViewProductComponent },
      { path: '', redirectTo: 'view', pathMatch: 'full' },
    ],
  },
  { path: 'about', redirectTo: 'products/about', pathMatch: 'full' },
  { path: '404', component: NotFoundComponent },
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: '**', redirectTo: '404' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
