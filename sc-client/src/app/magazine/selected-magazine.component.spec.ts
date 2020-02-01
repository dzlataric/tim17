import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedMagazineComponent } from './selected-magazine.component';

describe('SelectedMagazineComponent', () => {
  let component: SelectedMagazineComponent;
  let fixture: ComponentFixture<SelectedMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectedMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectedMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
