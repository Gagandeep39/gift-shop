import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

declare global {
  interface Window {
    StripeCheckout: any;
  }
}

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css'],
})
export class OrderHistoryComponent implements OnInit {
  constructor(private http: HttpClient) {}
  ngOnInit(): void {
    // this.loadStripe();
  }

  chargeCreditCard() {
    let form = document.getElementsByTagName('form')[0];
    (<any>window).Stripe.card.createToken(
      {
        number: form.cardNumber.value,
        exp_month: form.expMonth.value,
        exp_year: form.expYear.value,
        cvc: form.cvc.value,
      },
      (status: number, response: any) => {
        if (status === 200) {
          let token = response.id;
          this.chargeCard(token, 999);
        } else {
          console.log(response.error.message);
        }
      }
    );
  }

  chargeCard(token: string, amount: number) {
    // const headers = new HttpHeaders()
    let headers = new HttpHeaders().set('token', token); // create header object
    headers = headers.append('amount', amount.toString()); // add a new header, creating a new object
    console.log(headers);

    this.http
      .post('http://localhost:9600/payment/charge', {}, { headers })
      .subscribe((resp) => {
        console.log(resp);
      });
  }

}
