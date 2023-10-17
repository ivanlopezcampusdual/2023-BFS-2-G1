import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ExpensesHomeComponent } from "./expenses-home/expenses-home.component";
import { ExpensesNewComponent } from "./expenses-new/expenses-new.component";

const routes: Routes = [
  {
    path: "",
    component: ExpensesHomeComponent,
  },
  {
    path: "new",
    component: ExpensesNewComponent,
  },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ExpensesRoutingModule {}
