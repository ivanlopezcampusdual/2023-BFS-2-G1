import { Component, OnInit } from "@angular/core";
import * as moment from 'moment';
import { Expression, FilterExpressionUtils } from "ontimize-web-ngx";
import { ViewChildren, QueryList } from '@angular/core';
import { ChartSeries, PieChartConfiguration } from 'ontimize-web-ngx-charts';

@Component({
  selector: "app-expenses-home",
  templateUrl: "./expenses-home.component.html",
  styleUrls: ["./expenses-home.component.css"],
})
export class ExpensesHomeComponent implements OnInit {
  @ViewChildren('expenseTable') expenseTable: QueryList<any>;
  public selected ={};
  public date=[];


  constructor() {
    this.selected = {
      startDate: moment('1993-01-01T00:00Z'),
      endDate: moment(new Date())
    };


  }


  ngOnInit() {}




  clearFilters() {
    this.expenseTable.first.reloadData();
  }


  getValue() {
    return this.selected;
  }


  createFilter(values: Array<{ attr, value }>): Expression {
    let filters: Array<Expression> = [];

  console.log("Entramos en fecha")
    values.forEach(fil => {
      console.log(fil.value)
        if (fil.value) {
          if (fil.attr === 'date_range2') {
            filters.push(FilterExpressionUtils.buildExpressionMoreEqual('MOV_DATE', fil.value.startDate));
            filters.push(FilterExpressionUtils.buildExpressionLessEqual('MOV_DATE', fil.value.endDate));
            console.log(filters)
          }


          if (fil.attr === 'CA_ID') {
            let valueArray = Array.from(fil.value);
            if (valueArray.length > 1) {
              let filterExpressions = valueArray.map(value => FilterExpressionUtils.buildExpressionEquals('CA_ID', value));
              let filterExpression = filterExpressions.reduce((exp1, exp2) => FilterExpressionUtils.buildComplexExpression(exp1, exp2, FilterExpressionUtils.OP_OR));
              filters.push(filterExpression);
            } else {
              filters.push(FilterExpressionUtils.buildExpressionEquals('CA_ID', valueArray[0]));
            }
          }
        }
    });


    if (filters.length > 0 ) {
      const filterExpression = filters.reduce((exp1, exp2) => FilterExpressionUtils.buildComplexExpression(exp1, exp2, FilterExpressionUtils.OP_AND));
      return filterExpression;
    } else {
      return null;
    }
    }
  }
