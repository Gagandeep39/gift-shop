/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-24 01:57:04
 * @modify date 2021-01-24 01:57:04
 * @desc [description]
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { AuthModalService } from 'src/app/services/auth-modal.service';
import { AuthService } from 'src/app/services/auth.service';
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
    public loadingService: LoadingService,
    private eventService: EventService,
    private productService: ProductService,
    private modalService: AuthModalService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initProducts();
    this.subscribeToCategories();
    this.subscribeToSearchQuery();
  }

  initProducts() {
    this.fetchAll();
  }

  // Fetch from server
  fetchAll() {
    this.loadingService.enableLoading();
    this.productService.fetchAllProducts().subscribe((res: Product[]) => {
      this.productList = res;
      this.loadingService.disableLoading();
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
    if (this.checkForAuthAndShowPopUp()) {
      this.loadingService.enableLoading();
      setTimeout(() => {
        this.loadingService.disableLoading();
      }, 2000);
    }
  }
  checkForAuthAndShowPopUp() {
    if (this.authService.isAuthenticated()) return true;
    this.modalService.open(this.router.url);
    return false;
  }

  // Subbscribe to search
  subscribeToSearchQuery() {
    this.eventService.searchQueryChanged.subscribe((query) => {
      this.loadingService.enableLoading();
      // Fetch and unsubscribe
      if (!query) this.fetchAll();
      else
        this.productService.findByName(query).subscribe((res: Product[]) => {
          this.productQuery = query;
          this.activeCategory = null;
          this.productList = res;
          this.loadingService.disableLoading();
        }).closed;
    });
  }

  // Category change subscription
  subscribeToCategories() {
    this.eventService.categoryChanged.subscribe((category) => {
      this.loadingService.enableLoading();
      // Reset previous cateogry
      this.activeCategory = null;
      this.productQuery = null;
      if (!category) this.fetchAll();
      else
        this.productService
          .findByCategory(category)
          .subscribe((res: Product[]) => {
            this.productQuery = null;
            this.activeCategory = category;
            this.productList = res;
            this.loadingService.disableLoading();
          }).closed;
    });
  }
}
