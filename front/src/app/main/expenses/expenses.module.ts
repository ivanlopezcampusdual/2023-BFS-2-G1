import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ExpensesRoutingModule } from "./expenses-routing.module";
import { OntimizeWebModule } from "ontimize-web-ngx";
import { ExpensesHomeComponent } from "./expenses-home/expenses-home.component";
import { ExpensesNewComponent } from "./expenses-new/expenses-new.component";

@NgModule({
  declarations: [ExpensesHomeComponent, ExpensesNewComponent],
  imports: [CommonModule, ExpensesRoutingModule, OntimizeWebModule],
})
export class ExpensesModule {}
