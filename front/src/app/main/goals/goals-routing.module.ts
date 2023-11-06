import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GoalsHomeComponent } from './goals-home/goals-home.component';
import { GoalsNewComponent } from './goals-new/goals-new.component';


const routes: Routes = [
  {
  path : '',
  component: GoalsHomeComponent
},
{
  path : 'new',
  component: GoalsNewComponent
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GoalsRoutingModule { }
