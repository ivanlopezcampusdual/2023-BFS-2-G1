import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { IncomesHomeComponent } from "./incomes-home/incomes-home.component";
import { IncomesNewComponent } from "./incomes-new/incomes-new.component";

const routes: Routes = [
  {
    path: "",
    component: IncomesHomeComponent,
  },
  {
    path: "new",
    component: IncomesNewComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class IncomesRoutingModule {}
