import { Component, OnInit } from "@angular/core";
import { DiscreteBarChartConfiguration } from "ontimize-web-ngx-charts";

@Component({
  selector: "app-expenses-chart",
  templateUrl: "./expenses-chart.component.html",
  styleUrls: ["./expenses-chart.component.css"],
})
export class ExpensesChartComponent implements OnInit {
  public chartParameters: DiscreteBarChartConfiguration;

  constructor() {
    this.chartParameters = new DiscreteBarChartConfiguration();
    this.chartParameters.showXAxis = true;
    this.chartParameters.showYAxis = true;
    this.chartParameters.x1Axis.axisLabel = "DATE";
    this.chartParameters.y1Axis.axisLabel = "AMOUNT";
    this.chartParameters.margin.left = 100;
  }

  ngOnInit() {}
}
