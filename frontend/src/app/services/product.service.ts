/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-25 11:17:01
 * @modify date 2021-01-25 11:17:01
 * @desc [description]
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productServiceUrl = `${environment.protocol}${environment.applicationUrl}/${environment.productService}/products`;

  constructor(private http: HttpClient) { }

  fetchAllProducts() {
    return this.http.get(this.productServiceUrl);
  }

  findByName(name) {
    return this.http.get(`${this.productServiceUrl}/name/${name}`);
  }

  findByCategory(category) {
    return this.http.get(`${this.productServiceUrl}/category/${category}`);
  }

  fetchById(productId) {
    return this.http.get(`${this.productServiceUrl}/${productId}`);
  }
}
