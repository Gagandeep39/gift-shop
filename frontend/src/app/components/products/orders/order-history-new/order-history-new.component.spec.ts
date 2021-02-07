import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderHistoryNewComponent } from './order-history-new.component';

describe('OrderHistoryNewComponent', () => {
  let component: OrderHistoryNewComponent;
  let fixture: ComponentFixture<OrderHistoryNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderHistoryNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderHistoryNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
