import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/address.model';
import { Cart } from 'src/app/models/cart.model';
import { ProductInOrder } from 'src/app/models/product-in-order.model';
import { Product } from 'src/app/models/product.model';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { ManageUserService } from 'src/app/services/manage-user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  orderTotal = 0;
  tax: number = 0;
  total: number = 0;
  shipping: number = 0;
  address: Address = new Address();
  totalAmount: number;
  cart: Cart = new Cart();
  products = this.cart.products;
  userDetails;
  totalBeforeDiscount = 0;

  constructor(
    public cartService: CartService,
    private authService: AuthService,
    private manageUserService: ManageUserService
  ) {}

  ngOnInit(): void {
    this.fetchCart();
  }

  fetchCart() {
    this.cartService.fetchCartByUserId().subscribe((data: Cart) => {
      console.log(data);

      this.manageUserService
        .fetchById(this.authService.fetchFromSessionStorage()?.userId)
        .subscribe((user) => {
          this.address = user['address'];
          this.cart.cartId = data.cartId;
          this.cart.products = data.products;
          this.products = this.cart.products;
          this.calculateOrderSummary(this.products);
        });
    });
  }

  removeItem(productId: number) {
    console.log('Inside Remove');
    this.cartService.removeItemFromCart(productId).subscribe((data) => {
      this.fetchCart();
    });
  }

  calculateOrderSummary(productsInOrder: ProductInOrder[]) {
    console.log(productsInOrder);

    let discountPrice = 0;
    let totalBeforeDiscount = 0;
    let total;
    for (const key in productsInOrder) {
      console.log(productsInOrder[key]);
      totalBeforeDiscount += productsInOrder[key].productPrice;
      if (productsInOrder[key].discountPercent === 0)
        discountPrice = productsInOrder[key].productPrice;
      else discountPrice += this.calculatePrice(productsInOrder[key]);
    }
    this.totalBeforeDiscount = totalBeforeDiscount;

    this.orderTotal = discountPrice;

    if (this.orderTotal < 2000 && this.orderTotal > 0) {
      this.shipping = 40;
    } else {
      this.shipping = 0;
    }

    total = this.orderTotal + this.shipping;
    this.total = total;
  }

  checkout() {
    sessionStorage.setItem('shippingAddress', this.address.area);
    sessionStorage.setItem('City', this.address.city);
    sessionStorage.setItem('State', this.address.state);
    sessionStorage.setItem('Pincode', this.address.pincode);

    console.log(sessionStorage.getItem('shippingAddress'));
  }

  calculatePrice(currentProduct: ProductInOrder) {
    return (
      currentProduct.productPrice -
      currentProduct.productPrice * 0.01 * currentProduct.discountPercent
    );
  }
}
