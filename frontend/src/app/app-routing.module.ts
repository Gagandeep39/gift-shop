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
import { SocialRegisterComponent } from './components/auth/social-register/social-register.component';
import { AboutUsComponent } from './components/products/about-us/about-us.component';
import { CartComponent } from './components/products/cart/cart.component';
import { CartSummaryComponent } from './components/products/checkout/cart-summary/cart-summary.component';
import { CheckoutComponent } from './components/products/checkout/checkout.component';
import { PaymentComponent } from './components/products/checkout/payment/payment.component';
import { ShippingDetailsComponent } from './components/products/checkout/shipping-details/shipping-details.component';
import { DeliveryHistoryComponent } from './components/products/orders/delivery-history/delivery-history.component';
import { OrderDetailsComponent } from './components/products/orders/order-details/order-details.component';
import { OrderHistoryComponent } from './components/products/orders/order-history/order-history.component';
import { ProductDetailsComponent } from './components/products/product-details/product-details.component';
import { ProductListComponent } from './components/products/product-list/product-list.component';
import { ProductsComponent } from './components/products/products.component';
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';
import { AccessDeniedComponent } from './shared/access-denied/access-denied.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'socialregister', component: SocialRegisterComponent },
  { path: 'forgotpassword', component: RequestQuestionComponent },
  { path: 'changepassword', component: ChangePasswordComponent },
  {
    path: 'products',
    component: ProductsComponent,
    children: [
      { path: '', component: ProductListComponent },
      { path: 'cart', component: CartComponent, canActivate: [AuthGuard] },
      {
        path: 'orders',
        component: OrderHistoryComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'orders/:orderId',
        component: OrderDetailsComponent,
        canActivate: [AuthGuard],
      },
      { path: 'about', component: AboutUsComponent },
      {
        path: 'delivery/:orderId',
        component: DeliveryHistoryComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'checkout',
        component: CheckoutComponent,
        canActivate: [AuthGuard],
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
    canActivate: [AuthGuard, RoleGuard],
    data: { role: 'Admin' },
    children: [
      { path: 'add', component: AddProductComponent },
      { path: 'view', component: ViewProductComponent },
      { path: '', redirectTo: 'view', pathMatch: 'full' },
      { path: 'update/:productId', component: UpdateProductComponent },
    ],
  },
  { path: 'about', redirectTo: 'products/about', pathMatch: 'full' },
  { path: '404', component: NotFoundComponent },
  { path: '403', component: AccessDeniedComponent },
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: '**', redirectTo: '404' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
