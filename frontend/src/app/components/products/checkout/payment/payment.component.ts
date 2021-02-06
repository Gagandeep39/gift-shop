/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-02-06 17:25:05
 * @modify date 2021-02-06 17:25:05
 * @desc [description]
 */
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, take, tap } from 'rxjs/operators';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  address = {
    city: 'Mumbai',
    state: 'Maharashtra',
    area: 'Powai',
    pincode: '400072',
  };
  deliveryCharge = 20;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    // this.initDeliveryData();
  }

  initDeliveryData() {
    this.route.paramMap
      .pipe(
        map(() => window.history.state),
        take(1),
        tap((res) =>
          res?.address ? res : this.router.navigateByUrl('/products/checkout')
        )
      )
      .subscribe((res) => {
        this.deliveryCharge = res?.deliveryCharge;
        this.address = res?.address;
      });
  }

  goBack() {
    this.location.back();
  }

  payWithRazorpay() {}

  payWithStripe() {}
}
