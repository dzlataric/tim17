import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaypalFailedComponent } from './paypal-failed.component';

describe('PaypalFailedComponent', () => {
  let component: PaypalFailedComponent;
  let fixture: ComponentFixture<PaypalFailedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaypalFailedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaypalFailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
