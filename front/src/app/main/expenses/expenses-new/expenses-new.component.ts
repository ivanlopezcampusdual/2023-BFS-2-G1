import { Component, OnInit, ViewChild} from '@angular/core';
import { ValidatorFn} from "@angular/forms";
import { DatePipe } from '@angular/common';
import { ODateInputComponent, OValidators, OFormComponent } from "ontimize-web-ngx";

@Component({
  selector: "app-expenses-new",
  templateUrl: "./expenses-new.component.html",
  styleUrls: ["./expenses-new.component.css"],
})
export class ExpensesNewComponent implements OnInit {
  public userHasMadeChanges : boolean = false;
  validatorAmount: ValidatorFn[] = [];
  @ViewChild("dateInput", {static:false}) fieldFecha: ODateInputComponent;
  @ViewChild('newExpense', { static: false }) oForm: OFormComponent;

  constructor(private datePipe: DatePipe) {
    this.validatorAmount.push(
      OValidators.patternValidator(/^\d+([,.]\d+)?$/, 'negativeNumber'),
    );
    
  }

  ngOnInit() {
  }

  public addCurrentDate(event){
    if (event === 1) {
      this.fieldFecha.setValue(this.datePipe.transform(new Date(), "yyyy-MM-dd"));
      this.userHasMadeChanges = false;
    } 
  }

  public onUserChange(): void {
    this.userHasMadeChanges = true;
  }

  public resetCurrentDate(event): void{
    let closestSVG=  event.target.closest('mat-icon[svgicon="ontimize:undo"]'); 
    if((event.target.tagName === 'BUTTON' || event.target.tagName === 'SPAN'   && event.target.textContent.trim() === 'Deshacer')|| closestSVG ) 
     {
      this.fieldFecha.setValue(this.datePipe.transform(new Date(), "yyyy-MM-dd"));
    }
    this.userHasMadeChanges = false;
  } 
}

 

  

  
  

