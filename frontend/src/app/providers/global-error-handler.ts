import { Injectable, ErrorHandler } from '@angular/core';

/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-28 12:53:14
 * @modify date 2021-01-28 12:53:14
 * @desc [description]
 */
@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor() {}

  handleError(error: Error) {
    console.log(error);
  }
}
