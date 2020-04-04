import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogHistoryTripComponent } from './dialog-history-trip.component';

describe('DialogHistoryTripComponent', () => {
  let component: DialogHistoryTripComponent;
  let fixture: ComponentFixture<DialogHistoryTripComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogHistoryTripComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogHistoryTripComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
