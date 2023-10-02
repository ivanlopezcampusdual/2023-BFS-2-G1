import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ExpensesHomeComponent } from "./expenses-home/expenses-home.component";
import { ExpensesDetailComponent } from "./expenses-detail/expenses-detail.component";

const routes: Routes = [
  {
    path: "",
    component: ExpensesHomeComponent,
  },
  {
    path: ":EXPENSESID",
    component: ExpensesDetailComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ExpensesRoutingModule {}
