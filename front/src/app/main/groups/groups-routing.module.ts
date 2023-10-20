import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GroupsHomeComponent } from './groups-home/groups-home.component';
import { GroupsDetailComponent } from './groups-detail/groups-detail.component';


const routes: Routes = [
  {
    path: '',
    component: GroupsHomeComponent
  },
  {
    path: ':GR_ID',
    component: GroupsDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GroupsRoutingModule { }
