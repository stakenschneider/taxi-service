import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TakeTripComponent } from './take-trip.component';

describe('TakeTripComponent', () => {
  let component: TakeTripComponent;
  let fixture: ComponentFixture<TakeTripComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TakeTripComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TakeTripComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
