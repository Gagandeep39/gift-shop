import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductNewComponent } from './view-product-new.component';

describe('ViewProductNewComponent', () => {
  let component: ViewProductNewComponent;
  let fixture: ComponentFixture<ViewProductNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewProductNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewProductNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
