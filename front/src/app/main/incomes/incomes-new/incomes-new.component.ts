import { Component, OnInit } from '@angular/core';
import { ValidatorFn, ValidationErrors, FormControl } from "@angular/forms";

import { OValidators } from "ontimize-web-ngx";

@Component({
  selector: "app-incomes-new",
  templateUrl: "./incomes-new.component.html",
  styleUrls: ["./incomes-new.component.css"]
})
export class IncomesNewComponent implements OnInit {
  valor: number = 0;
  validatorAmount: ValidatorFn[] = [];

  constructor() { 
    this.validatorAmount.push(
      OValidators.patternValidator(/^\d+([,.]\d+)?$/, 'negativeNumber')
    );
  }

  ngOnInit() {
  }

}
