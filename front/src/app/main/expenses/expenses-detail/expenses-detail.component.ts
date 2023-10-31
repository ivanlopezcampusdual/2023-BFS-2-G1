import { Component, OnInit } from '@angular/core';
import { ValidatorFn } from '@angular/forms';
import { OValidators } from 'ontimize-web-ngx';

@Component({
  selector: 'app-expenses-detail',
  templateUrl: './expenses-detail.component.html',
  styleUrls: ['./expenses-detail.component.css']
})
export class ExpensesDetailComponent implements OnInit {
  validatorAmount: ValidatorFn[] = [];

  constructor() {
    this.validatorAmount.push(
      OValidators.patternValidator(/^\d+([,.]\d+)?$/, 'negativeNumber'),
    );
    
   }

  ngOnInit() {
  }

}
