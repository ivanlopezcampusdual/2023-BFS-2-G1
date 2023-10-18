import { Component, OnInit, ViewChild } from '@angular/core';
import { ValidatorFn } from "@angular/forms";
import { DatePipe } from '@angular/common';
import { ODateInputComponent, OValidators } from "ontimize-web-ngx";

@Component({
  selector: "app-incomes-new",
  templateUrl: "./incomes-new.component.html",
  styleUrls: ["./incomes-new.component.css"]
})
export class IncomesNewComponent implements OnInit {
  valor: number = 0;
  validatorAmount: ValidatorFn[] = [];
  @ViewChild("dateInput", {static:false}) fieldFecha: ODateInputComponent;

  constructor(private datePipe: DatePipe) { 
    this.validatorAmount.push(
      OValidators.patternValidator(/^\d+([,.]\d+)?$/, 'negativeNumber')
    );
  }

  ngOnInit() {
  }

  public addCurrentDate(event){
    this.fieldFecha.setValue(this.datePipe.transform(new Date(), "yyyy-MM-dd")); 
  }

}
