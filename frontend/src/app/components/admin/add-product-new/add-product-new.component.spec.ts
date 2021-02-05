import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductNewComponent } from './add-product-new.component';

describe('AddProductNewComponent', () => {
  let component: AddProductNewComponent;
  let fixture: ComponentFixture<AddProductNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddProductNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProductNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
