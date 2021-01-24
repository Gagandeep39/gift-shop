import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  count = 1;
  maxQuantity = 10;

  constructor() { }

  ngOnInit(): void {
  }

  increment() {
    if (this.count >= this.maxQuantity) this.count = this.maxQuantity;
    else this.count ++;
  }
  decrement() {
    if (this.count <= 1) this.count = 1;
    else this.count --;
  }

}
