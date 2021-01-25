/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-22 01:46:26
 * @modify date 2021-01-22 01:46:26
 * @desc [description]
 */
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { LoadingService } from 'src/app/services/loading.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  loginSubscription: Subscription;
  returnUrl;
  

  constructor(
    private authService: AuthService,
    private router: Router,
    public loadingService: LoadingService,
    private route: ActivatedRoute,
  ) {}

  ngOnDestroy(): void {
    if (this.loginSubscription) this.loginSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.authService.redirectIfLoggedIn();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.initForm();
  }

  login() {
    this.submitted = true;
    if (this.loginForm.valid) this.submitData(this.loginForm.value);
  }

  submitData(formData: any) {
    this.loadingService.enableLoading();
    this.loginSubscription = this.authService.login(formData).subscribe(
      (response) => {
        this.router.navigateByUrl(this.returnUrl);
        this.loadingService.disableLoading();
      },
      (error) => {
        this.loadingService.disableLoading();
        if (error.error.message === 'FieldException')
          error.error.errors.forEach((element) =>
            this.loginForm.controls[element.field]?.setErrors({
              serverValidationError: element.message,
            })
          );
        else throw new Error(error);
      }
    );
  }

  initForm() {
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

}
