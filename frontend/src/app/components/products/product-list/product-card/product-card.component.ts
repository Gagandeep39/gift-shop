/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-24 01:56:50
 * @modify date 2021-01-24 01:56:50
 * @desc [description]
 */
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { LoadingService } from 'src/app/services/loading.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css'],
})
export class ProductCardComponent implements OnInit {
  @Input()
  productName;
  @Input()
  productDescription;
  @Input()
  productImageUrl;
  @Input()
  productPrice;
  @Input()
  productId;
  @Input()
  productStatus;
  @Input()
  productQuantity;
  @Output()
  addToCartEvent = new EventEmitter<string>();

  constructor(public loadingService: LoadingService) {}

  ngOnInit(): void {}

  addNewItem(productId) {
    this.addToCartEvent.emit(productId);
  }

  isProductAvailable(): boolean {
    return this.productStatus !== 'ENABLED' || this.productQuantity <= 0;
  }
}
