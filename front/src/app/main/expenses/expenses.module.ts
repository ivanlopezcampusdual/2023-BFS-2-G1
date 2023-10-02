import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ExpensesRoutingModule } from "./expenses-routing.module";
import { OntimizeWebModule } from "ontimize-web-ngx";
import { ExpensesHomeComponent } from "./expenses-home/expenses-home.component";
import { ExpensesDetailComponent } from "./expenses-detail/expenses-detail.component";

@NgModule({
  declarations: [ExpensesHomeComponent, ExpensesDetailComponent],
  imports: [CommonModule, ExpensesRoutingModule, OntimizeWebModule],
})
export class ExpensesModule {}
