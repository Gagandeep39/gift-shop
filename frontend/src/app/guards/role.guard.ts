/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-25 14:49:14
 * @modify date 2021-01-25 14:49:14
 * @desc [description]
 */
import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.checkUserLogin(route, state.url);
  }

  checkUserLogin(route: ActivatedRouteSnapshot, url: any): boolean {
    if (this.authService.isAuthenticated()) {
      const userRole = this.authService.getRole();
      if (route.data.role && route.data.role.indexOf(userRole) === -1) {
        this.router.navigate(['/403']);
        return false;
      }
      return true;
    }

    this.router.navigate(['/403']);
    return false;
  }

  // TODO - Pass state.url to 403
  // Add a redirect nutton on 403
}
