import { Component, OnInit } from '@angular/core';
import { ValidatorFn, ValidationErrors, FormControl } from "@angular/forms";

import { OValidators } from "ontimize-web-ngx";

@Component({
  selector: "app-expenses-new",
  templateUrl: "./expenses-new.component.html",
  styleUrls: ["./expenses-new.component.css"],
})
export class ExpensesNewComponent implements OnInit {
  valor: number = 0;
  validatorAmount: ValidatorFn[] = [];

  constructor() {
    this.validatorAmount.push(
      OValidators.patternValidator(/^\d+([,.]\d+)?$/, 'negativeNumber'),
    );
  }

  ngOnInit() {
  }
}
