import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCategoryNewComponent } from './add-category-new.component';

describe('AddCategoryNewComponent', () => {
  let component: AddCategoryNewComponent;
  let fixture: ComponentFixture<AddCategoryNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCategoryNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCategoryNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
