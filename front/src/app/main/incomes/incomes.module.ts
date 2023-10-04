import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { IncomesRoutingModule } from './incomes-routing.module';
import { IncomesHomeComponent } from './incomes-home/incomes-home.component';


@NgModule({
  declarations: [IncomesHomeComponent],
  imports: [
    CommonModule,
    IncomesRoutingModule,
    IncomesRoutingModule
  ]
})
export class IncomesModule { }
