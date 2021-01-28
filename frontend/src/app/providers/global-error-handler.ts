/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-28 12:53:14
 * @modify date 2021-01-28 12:53:14
 * @desc [description]
 */
import { Injectable, ErrorHandler } from '@angular/core';
import { GlobalErrorModalService } from '../services/global-error-modal.service';
import { LoadingService } from '../services/loading.service';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(
    private modalService: GlobalErrorModalService,
    private loadingService: LoadingService
  ) {}

  handleError(error: Error) {
    console.log(error);
    this.loadingService.disableLoading();
    this.modalService.open(error);
  }
}
