import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryCarauselComponent } from './category-carausel.component';

describe('CategoryCarauselComponent', () => {
  let component: CategoryCarauselComponent;
  let fixture: ComponentFixture<CategoryCarauselComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CategoryCarauselComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryCarauselComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
