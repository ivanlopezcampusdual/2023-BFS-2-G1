import { Component, OnInit } from '@angular/core';
import { ValidatorFn } from '@angular/forms';
import { OValidators } from 'ontimize-web-ngx';

@Component({
  selector: 'app-incomes-detail',
  templateUrl: './incomes-detail.component.html',
  styleUrls: ['./incomes-detail.component.css']
})
export class IncomesDetailComponent implements OnInit {
  validatorAmount: ValidatorFn[] = [];
  constructor() { 
    this.validatorAmount.push(
      OValidators.patternValidator(/^\d+([,.]\d+)?$/, 'negativeNumber'),
    );
  }

  ngOnInit() {
  }

}
