/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-25 13:56:07
 * @modify date 2021-01-25 13:56:07
 * @desc Product Details
 */
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { AuthModalService } from 'src/app/services/auth-modal.service';
import { AuthService } from 'src/app/services/auth.service';
import { EventService } from 'src/app/services/event.service';
import { LoadingService } from 'src/app/services/loading.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css'],
})
export class ProductDetailsComponent implements OnInit {
  count = 1;
  productId;
  product: Product = null;

  constructor(
    public loadingService: LoadingService,
    private router: Router,
    private location: Location,
    private productService: ProductService,
    private route: ActivatedRoute,
    public eventService: EventService,
    private authService: AuthService,
    private authModalService: AuthModalService
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
    if (this.count >= this.product.productStock)
      this.count = this.product.productStock;
    else this.count++;
  }
  decrement() {
    if (this.count <= 1) this.count = 1;
    else this.count--;
  }

  addNewItem(productId) {
    if (this.checkForAuthAndShowPopUp()) {
      this.loadingService.enableLoading();
      setTimeout(() => {
        this.loadingService.disableLoading();
        this.router.navigateByUrl('/');
      }, 2000);
    }
  }

  goBack() {
    this.location.back();
  }

  redirectToCategory() {
    this.eventService.categoryChanged.next(this.product.categoryName);
    this.router.navigateByUrl('/');
  }

  checkForAuthAndShowPopUp() {
    if (this.authService.isAuthenticated()) return true;
    this.authModalService.open(this.router.url);
    return false;
  }
}
