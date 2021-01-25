/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-24 01:57:04
 * @modify date 2021-01-24 01:57:04
 * @desc [description]
 */
import { Component, OnInit } from '@angular/core';
import { EventService } from 'src/app/services/event.service';
import { LoadingService } from 'src/app/services/loading.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  productList = [
    {
      productId: 100001,
      productName: 'Cake',
      productImageUrl: '/assets/images/celebration2.jpg',
      productDescription:
        'A seet disk made from white and used for celebration',
      productPrice: 1200,
      productQuantity: 99,
      productStatus: 'ENABLED',
    },
    {
      productId: 100002,
      productName: 'Cake',
      productImageUrl: '/assets/images/celebration3.jpg',
      productDescription:
        'A seet disk made from white and used for celebration',
      productPrice: 1200,
      productQuantity: 99,
      productStatus: 'ENABLED',
    },
    {
      productId: 100003,
      productName: 'Cake',
      productImageUrl: '/assets/images/celebration.jpeg',
      productDescription:
        'A seet disk made from white and used for celebration',
      productPrice: 1200,
      productQuantity: 99,
      productStatus: 'DISABLED',
    },
    {
      productId: 100004,
      productName: 'Cake',
      productImageUrl: '/assets/images/celebration2.jpg',
      productDescription:
        'A seet disk made from white and used for celebration',
      productPrice: 1200,
      productQuantity: 0,
      productStatus: 'ENABLED',
    },
    {
      productId: 100005,
      productName: 'Cake',
      productImageUrl: '/assets/images/celebration2.jpg',
      productDescription:
        'A seet disk made from white and used for celebration',
      productPrice: 1200,
      productQuantity: 99,
      productStatus: 'DISABLED',
    },
    {
      productId: 100006,
      productName: 'Cake',
      productImageUrl: '/assets/images/confetti1.gif',
      productDescription:
        'A seet disk made from white and used for celebration',
      productPrice: 1200,
      productQuantity: 99,
      productStatus: 'ENABLED',
    },
    {
      productId: 100007,
      productName: 'Cake',
      productImageUrl: '/assets/images/confetti2.gif',
      productDescription:
        'A seet disk made from white and used for celebration',
      productPrice: 1200,
      productQuantity: 99,
      productStatus: 'ENABLED',
    },
  ];
  activeCategory = '';

  constructor(
    private loadingService: LoadingService,
    private eventService: EventService
  ) {}

  ngOnInit(): void {}

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
