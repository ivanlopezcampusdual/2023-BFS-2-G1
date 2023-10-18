import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { GroupsRoutingModule } from './groups-routing.module';
import { GroupsHomeComponent } from './groups-home/groups-home.component';


@NgModule({
  declarations: [GroupsHomeComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    GroupsRoutingModule
  ]
})
export class GroupsModule { }
