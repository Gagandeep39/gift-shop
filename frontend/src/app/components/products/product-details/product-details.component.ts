import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { LoadingService } from 'src/app/services/loading.service';
import { ProductService } from 'src/app/services/product.service';

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
  productId;
  product: Product = null;

  constructor(
    public loadingService: LoadingService,
    private router: Router,
    private location: Location,
    private productService: ProductService,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.productId = this.route.snapshot.paramMap.get('productId');
    this.initializeProductDetails();
  }

  initializeProductDetails() {
    this.productService.fetchById(this.productId).subscribe((res: Product) => {
      this.product = res;
    }).closed;
  }

  increment() {
    if (this.count >= this.product.productStock) this.count = this.product.productStock;
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
