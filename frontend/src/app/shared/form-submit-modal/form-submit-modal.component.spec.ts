import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormSubmitModalComponent } from './form-submit-modal.component';

describe('FormSubmitModalComponent', () => {
  let component: FormSubmitModalComponent;
  let fixture: ComponentFixture<FormSubmitModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormSubmitModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormSubmitModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
