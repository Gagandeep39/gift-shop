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
  productQuery = null;

  constructor(
    private loadingService: LoadingService,
    private eventService: EventService,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.subscribeToCategories();
    this.subscribeToSearchQuery();
    this.initProducts();
  }

  initProducts() {
    this.fetchAll();
  }

  // Fetch from server
  fetchAll() {
    this.productService.fetchAllProducts().subscribe((res: Product[]) => {
      this.productList = res;
    }).closed;
  }

  // Reset all search queries
  resetAll() {
    this.eventService.categoryChanged.next(null);
    this.eventService.searchQueryChanged.next(null);
    this.productQuery = null;
    this.productList = null;
    this.activeCategory = null;
    this.fetchAll();
  }

  addToCart(itemId) {
    console.log(itemId);
    this.loadingService.enableLoading();
    setTimeout(() => {
      this.loadingService.disableLoading();
    }, 2000);
  }

  // Subbscribe to search
  subscribeToSearchQuery() {
    this.eventService.searchQueryChanged.subscribe((query) => {
      // Fetch and unsubscribe
      if (!query) this.fetchAll();
      else
        this.productService.findByName(query).subscribe((res: Product[]) => {
          this.productQuery = query;
          this.activeCategory = null;
          this.productList = res;
        }).closed;
    });
  }

  // Category change subscription
  subscribeToCategories() {
    this.eventService.categoryChanged.subscribe((category) => {
      // Reset previous cateogry
      this.activeCategory = null;
      this.productQuery = null;
      if (!category) this.fetchAll();
      else
        this.productService
          .findByCategory(category?.categoryName)
          .subscribe((res: Product[]) => {
            this.productQuery = null;
            this.activeCategory = category;
            this.productList = res;
          }).closed;
    });
  }
}
