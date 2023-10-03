import { Component, OnInit } from '@angular/core';
import { ValidatorFn, ValidationErrors, FormControl } from "@angular/forms";

import { OValidators } from "ontimize-web-ngx";

@Component({
  selector: "app-expenses-detail",

  templateUrl: "./expenses-detail.component.html",

  styleUrls: ["./expenses-detail.component.css"],
})
export class ExpensesDetailComponent implements OnInit {
  valor: number = 0;

  validatorAmount: ValidatorFn[] = [];

  constructor() {
    this.validatorAmount.push(
      OValidators.patternValidator(/^\d+(\.\d{1,2})?$/, "negativeNumber")
    );
  }

  ngOnInit() {}
}
