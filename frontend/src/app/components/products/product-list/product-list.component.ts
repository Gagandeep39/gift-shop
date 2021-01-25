/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-24 01:57:04
 * @modify date 2021-01-24 01:57:04
 * @desc [description]
 */
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { EventService } from 'src/app/services/event.service';
import { LoadingService } from 'src/app/services/loading.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  productList: Product[] = [];
  activeCategory = null;

  constructor(
    private loadingService: LoadingService,
    private eventService: EventService,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.subscribeToCategories();
    this.initProducts();
  }

  initProducts() {
    this.productService.fetchAllProducts().subscribe((res: Product[]) => {
      this.productList = res;
    });
  }
  subscribeToCategories() {
    this.eventService.categoryChanged.subscribe((res) => {
      this.activeCategory = res;
    });
  }

  addToCart(itemId) {
    console.log(itemId);
    this.loadingService.enableLoading();
    setTimeout(() => {
      this.loadingService.disableLoading();
    }, 2000);
  }

  resetCategory() {
    this.eventService.categoryChanged.next(null);
  }
}
