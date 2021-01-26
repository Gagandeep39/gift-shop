import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Item } from '../models/item.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartServiceUrl = `${environment.protocol}${environment.applicationUrl}/${environment.cartService}/cart`;

  constructor(private http: HttpClient, private authService: AuthService) { }

  addToCart(item: Item) {
    return this.http.put(`${this.cartServiceUrl}/${this.authService.fetchFromSessionStorage()?.userId}`, item);
  }

  fetchCartByUserId() {
    return this.http.get(`${this.cartServiceUrl}/${this.authService.fetchFromSessionStorage()?.userId}`);
  }

  removeItemFromCart(productId) {
    return this.http.delete(`${this.cartServiceUrl}/${productId}/${this.authService.fetchFromSessionStorage()?.userId}`)
  }
}
