import { Component, OnInit } from "@angular/core";
import * as moment from "moment";
import { Expression, FilterExpressionUtils } from "ontimize-web-ngx";
import { ViewChildren, QueryList } from "@angular/core";

@Component({
  selector: "app-incomes-home",
  templateUrl: "./incomes-home.component.html",
  styleUrls: ["./incomes-home.component.css"],
})
export class IncomesHomeComponent implements OnInit {
  public selected = {};
  public date = [];

  @ViewChildren("incomeTable") incomeTable: QueryList<any>;
  constructor() {
    this.selected = {
      startDate: moment("1993-01-01T00:00Z"),
      endDate: moment(new Date()),
    };
  }

  ngOnInit() {}

  getValue() {
    return this.selected;
  }
  clearFilters() {
    this.incomeTable.first.reloadData();
  }

  createFilter(values: Array<{ attr; value }>): Expression {
    let filters: Array<Expression> = [];
    values.forEach((fil) => {
      if (fil.value) {
        if (fil.attr === "date_range") {
          filters.push(
            FilterExpressionUtils.buildExpressionMoreEqual(
              "MOV_DATE",
              fil.value.startDate
            )
          );
          filters.push(
            FilterExpressionUtils.buildExpressionLessEqual(
              "MOV_DATE",
              fil.value.endDate
            )
          );
        }

        if (fil.attr === "CA_ID") {
          let valueArray = Array.from(fil.value);
          if (valueArray.length > 1) {
            let filterExpressions = valueArray.map((value) =>
              FilterExpressionUtils.buildExpressionEquals("CA_ID", value)
            );
            let filterExpression = filterExpressions.reduce((exp1, exp2) =>
              FilterExpressionUtils.buildComplexExpression(
                exp1,
                exp2,
                FilterExpressionUtils.OP_OR
              )
            );
            filters.push(filterExpression);
          } else {
            filters.push(
              FilterExpressionUtils.buildExpressionEquals(
                "CA_ID",
                valueArray[0]
              )
            );
          }
        }
      }
    });

    if (filters.length > 0) {
      return filters.reduce((exp1, exp2) =>
        FilterExpressionUtils.buildComplexExpression(
          exp1,
          exp2,
          FilterExpressionUtils.OP_AND
        )
      );
    } else {
      return null;
    }
  }
}
