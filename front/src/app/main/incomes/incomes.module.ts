import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { IncomesRoutingModule } from './incomes-routing.module';
import { IncomesHomeComponent } from './incomes-home/incomes-home.component';
import { IncomesNewComponent } from './incomes-new/incomes-new.component';


@NgModule({
  declarations: [IncomesHomeComponent, IncomesNewComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    IncomesRoutingModule
  ]
})
export class IncomesModule { }
