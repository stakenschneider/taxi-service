import {Component, OnInit} from '@angular/core';
import {GetData} from '../../../models/table/get.data.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  getData: any = {};
  parameters: Map<string, any> = new Map<string, any>();


  constructor() {
  }

  ngOnInit(): void {
    this.getData.dataName = 'tripTable';
    this.parameters.set('for', 'ADMIN');
    this.getData.parameters = this.parameters;
  }
}
