/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-29 10:40:53
 * @modify date 2021-01-29 10:40:53
 * @desc [description]
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SocialAuthService {

  
  authServiceUrl = `${environment.protocol}${environment.applicationUrl}/${environment.authService}/social`;

  constructor(private http: HttpClient) { }

  validateGoogleTokenAndLogin(data) {
    return this.http.post(`${this.authServiceUrl}/google`, data);
  }

  socialSignUp(data) {
    return this.http.post(`${this.authServiceUrl}/signup`, data);
  }
}
