/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-29 10:40:53
 * @modify date 2021-01-29 10:40:53
 * @desc [description]
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';
import { AuthService } from './auth.service';
import { EventService } from './event.service';

@Injectable({
  providedIn: 'root',
})
export class SocialAuthService {
  authServiceUrl = `${environment.protocol}${environment.applicationUrl}/${environment.authService}/social`;

  constructor(
    private http: HttpClient,
    private eventServie: EventService,
    private authService: AuthService
  ) {}

  validateGoogleTokenAndLogin(data) {
    return this.http
      .post(`${this.authServiceUrl}/google`, data)
      .pipe(tap((user: User) => this.triggerLogin(user)));
  }

  validateFacebookTokenAndLogin(data) {
    return this.http
      .post(`${this.authServiceUrl}/facebook`, data)
      .pipe(tap((user: User) => this.triggerLogin(user)));
  }

  socialSignUp(data) {
    return this.http
      .post(`${this.authServiceUrl}/signup`, data)
      .pipe(tap((user: User) => this.triggerLogin(user)));
  }

  triggerLogin(user: User) {
    this.authService.saveToSessionStorage(user);
    this.eventServie.loggedInUser.next(user);
  }
}
