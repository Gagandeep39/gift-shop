/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-25 22:36:58
 * @modify date 2021-01-25 22:36:58
 * @desc [description]
 */
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeliveryHistory } from 'src/app/models/delivery-history.model';
import { OrderStatus } from 'src/app/models/order-status.model';
import { LoadingService } from 'src/app/services/loading.service';
import { OrderCancelModalService } from 'src/app/services/order-cancel-modal.service';
@Component({
  selector: 'app-delivery-history',
  templateUrl: './delivery-history.component.html',
  styleUrls: ['./delivery-history.component.css'],
})
export class DeliveryHistoryComponent implements OnInit {
  OrderStaus = OrderStatus;
  orderId;
  deliveryHistory: DeliveryHistory[] = [
    {
      orderId: 100001,
      deliveryId: 100001,
      orderStatus: 'NEW',
      updatedOn: new Date('2021-01-21'),
    },
    {
      orderId: 100001,
      deliveryId: 100002,
      orderStatus: 'DISPATCHED',
      updatedOn: new Date('2021-01-22'),
    },
    {
      orderId: 100001,
      deliveryId: 100003,
      orderStatus: 'OUT_FOR_DELIVERY',
      updatedOn: new Date('2021-01-23'),
    },
    {
      orderId: 100001,
      deliveryId: 100004,
      orderStatus: 'DELIVERED',
      updatedOn: new Date('2021-01-24'),
    },
  ];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    public loadingService: LoadingService,
    private orderCancelModal: OrderCancelModalService
  ) {
    console.log(new Date('2021-01-23'));
  }

  ngOnInit(): void {
    this.orderId = this.route.snapshot.params['orderId'];
  }

  formattedDate(date) {
    return date.toLocaleDateString('en-US', {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric',
    });
  }

  goBack() {
    this.location.back();
  }

  cancelOrder() {
    this.orderCancelModal.open();
    this.orderCancelModal.watch().subscribe((res) => {
      this.loadingService.enableLoading();
      if (res === 'cancel-order')
        setTimeout(() => {
          // Cancel order and fetch order details
          console.log('Order cancelled');
          this.loadingService.disableLoading();
        }, 2000);

    }).closed;
  }
}
