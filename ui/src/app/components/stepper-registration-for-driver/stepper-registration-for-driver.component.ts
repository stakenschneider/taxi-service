import {Component, OnInit} from '@angular/core';
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
  passportFormGroup: FormGroup;
  carFormGroup: FormGroup;
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
      passportSeries: ['', Validators.required],
      passportNumber: ['', Validators.required]
      // TODO опять надо залупиться по поводу валидаторов
      // , Validators.pattern('[0-9]{6}')
    });

    this.carFormGroup = this._formBuilder.group({
      carNumber: ['', Validators.required],
      carModel: ['', Validators.required],
      carColor: ['', Validators.required]
    });

    // TODO load when open or when person is DRIVER
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
    this.setCar();
    // TODO надо дебажить, вроде если запускать их подря, то таблица
    // с водителем не успевает записать ид машины но мне лень пока так что жестко атхардкожено
    setTimeout(() => {
      this.setPassport();
    }, 3000);
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
