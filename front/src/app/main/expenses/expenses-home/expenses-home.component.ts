import { Component, OnInit , AfterViewInit,ElementRef   } from '@angular/core';
import { PieChartConfiguration } from 'ontimize-web-ngx-charts';
import { D3LocaleService } from '../../../shared/d3-locale/d3Locale.service';
import { OTableComponent } from 'ontimize-web-ngx';
import { JsonPipe } from '@angular/common';


@Component({
  selector: 'app-expenses-home',
  templateUrl: './expenses-home.component.html',
  styleUrls: ['./expenses-home.component.css']
})
export class ExpensesHomeComponent implements OnInit  {
  public movementTypesChartParams: PieChartConfiguration;
  datosTabla: any[] = [];
  private expenseTable: OTableComponent;
  

  constructor(
    private d3LocaleService: D3LocaleService,  private el: ElementRef
  ) {
    const d3Locale = this.d3LocaleService.getD3LocaleConfiguration();
    this._configurePieChart(d3Locale);
  

   }

   ngAfterViewInit(): void {
    this.expenseTable = this.el.nativeElement.querySelector('#expenseTable');
    if (this.expenseTable) {
      const datosTabla = this.expenseTable.dataArray;
      console.log('Datos de la tabla:', datosTabla);
      
    }
   }

  ngOnInit() {
    
   }
   onClickObtenerDatosTabla(): void {
    if (this.expenseTable) {
    const datosTabla = this.expenseTable.dataArray;
    console.log('Datos de la tabla:', datosTabla);
    }

  }
  private _configurePieChart(locale: any): void {
    this.movementTypesChartParams = new PieChartConfiguration();
    this.movementTypesChartParams.margin.top = 0;
    this.movementTypesChartParams.margin.right = 0;
    this.movementTypesChartParams.margin.bottom = 0;
    this.movementTypesChartParams.margin.left = 0;
    this.movementTypesChartParams.legendPosition = 'bottom';
    this.movementTypesChartParams.legend.vers = 'furious';
    this.movementTypesChartParams.labelType = 'value';
    this.movementTypesChartParams.valueType = locale.numberFormat('$,.3f');



    
  }

}


