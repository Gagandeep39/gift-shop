// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  protocol: 'http://',
  applicationUrl: 'localhost:9000',
  authService: 'auth-service',
  productService: 'product-service',
  orderService: 'order-service',
  paymentService: 'payment-service',
  cartService: 'cart-service',
  mapQuestAPIKey: 'nXLMbpX6U0545mAaAJyeI2QQd1JYVqG4',
  mapQuestUrl: 'http://www.mapquestapi.com/directions/v2/route',
  publicStripeKey: 'pk_test_51I3zWOI8py6Rj5ufY6RnJ1zjPK930I2xwWW0XKPWFr6cp7mAvgM8q8aoAV5R0trqmGEEqSgrLY6xVhZRqTsgZJqE00zW2TqkZa',
  publicRazorpayKey: 'rzp_test_QHK5P7ne66SdWo',
  from: '400072',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
