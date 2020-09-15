import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DriverService} from '../../../services/driver.service';
import {DataService} from '../../../services/data.service';
import {StoreService} from '../../../services/store.service';

@Component({
  selector: 'app-stepper-registration-for-driver',
  templateUrl: './stepper-registration-for-driver.component.html',
  styleUrls: ['./stepper-registration-for-driver.component.css']
})
export class StepperRegistrationForDriverComponent implements OnInit {
  @Input() person: any;
  passportFormGroup: FormGroup;
  carFormGroup: FormGroup;
  phoneForm: FormGroup;
  public carColors: Array<string>;
  public carModels: Array<string>;

  constructor(private dataService: DataService,
              // tslint:disable-next-line:variable-name
              private _formBuilder: FormBuilder, private driverService: DriverService, private storeService: StoreService) {
    this.driverService = driverService;
    this.storeService = storeService;
    this.dataService = dataService;
  }

  ngOnInit(): void {
    this.passportFormGroup = this._formBuilder.group({
      // TODO if passport exist change stepper to 2 step
      passportSeries: [this.person.passport ? this.person.passport.series : '', Validators.required],
      passportNumber: [this.person.passport ? this.person.passport.number : '', Validators.required]
      // TODO опять надо залупиться по поводу валидаторов
      // , Validators.pattern('[0-9]{6}')
    });

    this.carFormGroup = this._formBuilder.group({
      carNumber: [this.person.car ? this.person.car.number : '', Validators.required],
      carModel: [this.person.car ? this.person.car.model : '', Validators.required],
      carColor: [this.person.car ? this.person.car.color : '', Validators.required]
    });

    this.phoneForm = this._formBuilder.group({
      phoneNumber: [this.person.phoneNumber ? this.person.phoneNumber : '', Validators.required]
    });

    this.dataService.getCarColorList().subscribe(
      data => {
        this.carColors = data;
      });

    this.dataService.getCarModelList().subscribe(
      data => {
        this.carModels = data;
      });
  }

  save() {

  }

  setCar() {
    this.driverService.setCar(this.storeService.getId(),
      this.carFormGroup.controls.carNumber.value,
      this.carFormGroup.controls.carModel.value, this.carFormGroup.controls.carColor.value).subscribe(
      data => {
        // alert(data.message);
      }, error => alert(error)
    );

  }

  setPassport() {
    this.driverService.setPassport(this.storeService.getId(),
      this.passportFormGroup.controls.passportSeries.value,
      this.passportFormGroup.controls.passportNumber.value).subscribe(
      data => {
        // alert(data.message);
      }, error => alert(error)
    );
  }
}
