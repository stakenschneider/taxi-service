import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StepperRegistrationForDriverComponent } from './stepper-registration-for-driver.component';

describe('StepperRegistrationForDriverComponent', () => {
  let component: StepperRegistrationForDriverComponent;
  let fixture: ComponentFixture<StepperRegistrationForDriverComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StepperRegistrationForDriverComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StepperRegistrationForDriverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
