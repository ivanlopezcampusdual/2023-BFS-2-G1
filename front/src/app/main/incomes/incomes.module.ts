import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { IncomesRoutingModule } from './incomes-routing.module';
import { IncomesHomeComponent } from './incomes-home/incomes-home.component';
import { IncomesNewComponent } from './incomes-new/incomes-new.component';
import { OChartModule } from 'ontimize-web-ngx-charts';
import { SharedModule } from "src/app/shared/shared.module";
import { IncomesChartComponent } from './incomes-chart/incomes-chart.component';

@NgModule({
  declarations: [IncomesHomeComponent, IncomesNewComponent, IncomesChartComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    IncomesRoutingModule, 
    OChartModule, 
    SharedModule
  ], exports: [
    IncomesChartComponent
  ]
})
export class IncomesModule { }
