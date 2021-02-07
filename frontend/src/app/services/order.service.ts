/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-26 11:35:44
 * @modify date 2021-01-26 11:35:44
 * @desc [description]
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { UpdateOrderStatus } from '../models/update-order-status.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  orderServiceUrl = `${environment.protocol}${environment.applicationUrl}/${environment.orderService}/orders`;

  constructor(private http: HttpClient, private authService: AuthService) {}

  updateOrderStatus(data: UpdateOrderStatus) {
    return this.http.post(`${this.orderServiceUrl}`, data);
  }

  fetchOrder() {
    return this.http.get(`${this.orderServiceUrl}`);
  }

  fetchById(id) {
    return this.http.get(`${this.orderServiceUrl}/${id}`);
  }

  fetchByLoggedInUserId() {
    return this.fetchByUserId(
      this.authService.fetchFromSessionStorage()?.userId
    );
  }

  fetchByUserId(userId) {
    return this.http.get(`${this.orderServiceUrl}/user/${userId}`);
  }
}
