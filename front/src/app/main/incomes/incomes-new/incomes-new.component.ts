import { Component, OnInit, ViewChild, Inject } from "@angular/core";
import { ValidatorFn } from "@angular/forms";
import { DatePipe } from "@angular/common";
import {
  ODateInputComponent,
  OValidators,
  OFormComponent,
} from "ontimize-web-ngx";
import { MAT_DIALOG_DATA } from "@angular/material";
import { MatDialogModule, MatDialogRef } from "@angular/material/dialog";
import { Router } from "@angular/router";
@Component({
  selector: "app-incomes-new",
  templateUrl: "./incomes-new.component.html",
  styleUrls: ["./incomes-new.component.css"],
})
export class IncomesNewComponent implements OnInit {
  public userHasMadeChanges: boolean = false;
  valor: number = 0;
  validatorAmount: ValidatorFn[] = [];
  @ViewChild("dateInput", { static: false }) fieldFecha: ODateInputComponent;
  @ViewChild("newIncome", { static: false }) oForm: OFormComponent;

  @ViewChild("newIncome", { static: false }) form: OFormComponent;
  public dialog: MatDialogModule;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<IncomesNewComponent>,
    private datePipe: DatePipe,
    private router: Router
  ) {
    this.validatorAmount.push(
      OValidators.patternValidator(/^\d+([,.]\d+)?$/, "negativeNumber")
    );
  }

  ngOnInit() {}
  public forceInsertMode(event: any) {
    if (event != OFormComponent.Mode().INSERT) {
      this.form.setInsertMode();
      this.form.setFieldValues(this.data);
    }
  }

  public closeDialog(event: any) {
    this.dialogRef.close();
  }
  onCancel() {
    this.dialogRef.close();
    this.router.navigate([this.data.cancelUrl]);
  }
  public addCurrentDate(event) {
    if (event === 1) {
      console.log({ event });
      this.fieldFecha.setValue(
        this.datePipe.transform(new Date(), "yyyy-MM-dd")
      );
      this.userHasMadeChanges = false;
    }
  }

  public onUserChange(): void {
    this.userHasMadeChanges = true;
  }

  public resetCurrentDate(event): void {
    let closestSVG = event.target.closest('mat-icon[svgicon="ontimize:undo"]');
    if (
      event.target.tagName === "BUTTON" ||
      (event.target.tagName === "SPAN" &&
        event.target.textContent.trim() === "Deshacer") ||
      closestSVG
    ) {
      this.fieldFecha.setValue(
        this.datePipe.transform(new Date(), "yyyy-MM-dd")
      );
    }
    this.userHasMadeChanges = false;
  }
}
