/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-02-06 17:25:05
 * @modify date 2021-02-06 17:25:05
 * @desc [description]
 */
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, take, tap } from 'rxjs/operators';
import { Address } from 'src/app/models/address.model';
import { Cart } from 'src/app/models/cart.model';
import { ProductInOrder } from 'src/app/models/product-in-order.model';
import { CartService } from 'src/app/services/cart.service';
import { ManageUserService } from 'src/app/services/manage-user.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  address: Address;
  deliveryCharge: number;
  totalAfterDiscount: number;
  totalBeforeDiscount: number;
  finalTotal: number;
  user;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private location: Location,
    private cartService: CartService,
    private manageUserService: ManageUserService
  ) {}

  ngOnInit(): void {
    this.initDeliveryData();
    this.fetchCartData();
    this.fetchUserData();
  }
  fetchUserData() {
    this.manageUserService
      .fetchLoggedInUserForEdit()
      .pipe(take(1))
      .subscribe({ next: (res) => (this.user = res) });
  }
  fetchCartData() {
    this.cartService
      .fetchCartByUserId()
      .pipe(take(1))
      .subscribe({
        next: (res: Cart) => this.calculateOrderSummary(res.products),
      });
  }

  initDeliveryData() {
    this.route.paramMap
      .pipe(
        map(() => window.history.state),
        take(1),
        tap((res) =>
          res?.address ? res : this.router.navigateByUrl('/products/checkout')
        )
      )
      .subscribe((res) => {
        this.deliveryCharge = res?.deliveryCharge;
        this.address = res?.address;
      });
  }

  goBack() {
    this.location.back();
  }

  payWithRazorpay() {}

  payWithStripe() {}

  calculateOrderSummary(productsInOrder: ProductInOrder[]) {
    console.log(productsInOrder);

    let discountPrice = 0;
    let totalBeforeDiscount = 0;
    let finalTotal;
    for (const key in productsInOrder) {
      console.log(productsInOrder[key]);
      totalBeforeDiscount += productsInOrder[key].productPrice;
      if (productsInOrder[key].discountPercent === 0)
        discountPrice = productsInOrder[key].productPrice;
      else discountPrice += this.calculatePrice(productsInOrder[key]);
    }
    this.totalBeforeDiscount = totalBeforeDiscount;

    this.totalAfterDiscount = discountPrice;
    finalTotal = this.totalAfterDiscount + this.deliveryCharge;

    this.finalTotal = finalTotal;
  }

  calculatePrice(currentProduct: ProductInOrder) {
    return (
      currentProduct.productPrice -
      currentProduct.productPrice * 0.01 * currentProduct.discountPercent
    );
  }

  checkOutInServer(res) {
    const data = {
      ...this.address,
      deliveryCharge: this.deliveryCharge,
      paymentId: res.paymentId,
    };
    this.cartService.checkout(data).subscribe((res) => {
      console.log(res);
      this.router.navigateByUrl('/products/delivery/' + res['orderId']);
    });
  }
}
