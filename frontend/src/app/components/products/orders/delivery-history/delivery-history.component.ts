/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-25 22:36:58
 * @modify date 2021-01-25 22:36:58
 * @desc [description]
 */
import { Component, OnInit } from '@angular/core';
import { DeliveryHistory } from 'src/app/models/delivery-history.model';

@Component({
  selector: 'app-delivery-history',
  templateUrl: './delivery-history.component.html',
  styleUrls: ['./delivery-history.component.css'],
})
export class DeliveryHistoryComponent implements OnInit {
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

  constructor() {}

  ngOnInit(): void {}
}
