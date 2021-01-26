/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-26 16:48:58
 * @modify date 2021-01-26 16:48:58
 * @desc [description]
 */
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/models/category.model';
import { Product } from 'src/app/models/product.model';
import { CategoryService } from 'src/app/services/category.service';
import { LoadingService } from 'src/app/services/loading.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css'],
})
export class UpdateProductComponent implements OnInit {
  productId;
  submitted = false;
  updateProductForm: FormGroup;
  categories: Category[] = [];
  statuses = ['ENABLED', 'DISABLED'];
  product: Product;
  productIcon = '';

  constructor(
    private route: ActivatedRoute,
    public loadingService: LoadingService,
    private productService: ProductService,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['productId'] || '';
    this.initForm();
    this.populateData();
  }
  initForm() {
    this.updateProductForm = new FormGroup({
      productId: new FormControl(this.productId),
      productName: new FormControl('', [Validators.required]),
      productPrice: new FormControl('', [Validators.required]),
      productStock: new FormControl('', [Validators.required]),
      productDescription: new FormControl('', [Validators.required]),
      productIcon: new FormControl('', [Validators.required]),
      productStatus: new FormControl('', [Validators.required]),
      categoryId: new FormControl('', [Validators.required]),
    });
  }
  populateData() {
    this.categoryService.fetchAllCategories().subscribe((res: Category[]) => {
      this.categories = res;
    }).closed;
    this.productService.fetchById(this.productId).subscribe((res: Product) => {
      this.product = res;
      this.productIcon = res.productIcon; // Apply image fetched from server
      this.populateFormFields(this.product);
    }).closed;
  }

  populateFormFields(product) {
    this.updateProductForm.patchValue({
      productId: product.productId,
      productName: product?.productName,
      productPrice: product?.productPrice,
      productStock: product?.productStock,
      productDescription: product?.productDescription,
      productIcon: product?.productIcon,
      productStatus: product?.productStatus,
      categoryId: product?.categoryId,
    });
  }

  submitForm() {
    this.submitted = true;
    if (this.updateProductForm.valid)
      this.submitData(this.updateProductForm.value);
  }
  submitData(formData: any) {
    this.loadingService.enableLoading();
    this.productService.updateProduct(formData).subscribe(
      (response) => {
        console.log(response);

        this.loadingService.disableLoading();
      },
      (error) => {
        this.loadingService.disableLoading();
        if (error?.error?.message === 'FieldException')
          error.error.errors.forEach((element) =>
            this.updateProductForm.controls[element.field]?.setErrors({
              serverValidationError: element.message,
            })
          );
        else throw new Error(error);
      }
    );
  }

  updateImage(image) {
    console.log('triggered');

    this.productIcon = image;
  }

  resetToDefault() {
    this.populateFormFields(this.product);
    this.productIcon = this.product.productIcon;
  }
}
