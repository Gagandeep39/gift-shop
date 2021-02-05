import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateStatusModalComponent } from './update-status-modal.component';

describe('UpdateStatusModalComponent', () => {
  let component: UpdateStatusModalComponent;
  let fixture: ComponentFixture<UpdateStatusModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateStatusModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateStatusModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
