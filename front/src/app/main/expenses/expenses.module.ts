import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ExpensesRoutingModule } from "./expenses-routing.module";
import { OntimizeWebModule } from "ontimize-web-ngx";
import { ExpensesHomeComponent } from "./expenses-home/expenses-home.component";
import { ExpensesNewComponent } from "./expenses-new/expenses-new.component";
import { OChartModule } from 'ontimize-web-ngx-charts';
import { SharedModule } from "src/app/shared/shared.module";


@NgModule({
  declarations: [ExpensesHomeComponent, ExpensesNewComponent],
  imports: [CommonModule, ExpensesRoutingModule, OntimizeWebModule, OChartModule, SharedModule],
})
export class ExpensesModule {}
