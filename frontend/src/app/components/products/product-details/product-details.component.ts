import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingService } from 'src/app/services/loading.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css'],
})
export class ProductDetailsComponent implements OnInit {
  count = 1;
  maxQuantity = 10;
  productImageUrl = '/assets/images/celebration2.jpg';
  productName;
  productQuantity;
  productDescription;
  productCategory;
  productPrice;

  constructor(
    public loadingService: LoadingService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {}

  increment() {
    if (this.count >= this.maxQuantity) this.count = this.maxQuantity;
    else this.count++;
  }
  decrement() {
    if (this.count <= 1) this.count = 1;
    else this.count--;
  }

  addNewItem(productId) {
    this.loadingService.enableLoading();
    setTimeout(() => {
      this.loadingService.disableLoading();
      this.router.navigateByUrl('/');
    }, 2000);
  }

  goBack() {
    this.location.back();
  }
}
