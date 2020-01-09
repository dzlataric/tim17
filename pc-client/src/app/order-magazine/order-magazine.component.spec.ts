import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderMagazineComponent } from './order-magazine.component';

describe('OrderMagazineComponent', () => {
  let component: OrderMagazineComponent;
  let fixture: ComponentFixture<OrderMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
