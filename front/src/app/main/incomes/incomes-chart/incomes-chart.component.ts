import { Component, OnInit, Injector, ViewChild } from "@angular/core";
import { OntimizeService, OTranslateService } from "ontimize-web-ngx";

import {
  ChartService,
  OChartComponent,
  DiscreteBarChartConfiguration,
} from "ontimize-web-ngx-charts";
import { D3LocaleService } from "src/app/shared/d3-locale/d3Locale.service";
import { D3Locales } from "src/app/shared/d3-locale/locales";

@Component({
  selector: "app-incomes-chart",
  templateUrl: "./incomes-chart.component.html",
  styleUrls: ["./incomes-chart.component.css"],
  providers: [ChartService],
})
export class IncomesChartComponent implements OnInit {
  @ViewChild("discreteBar", { static: false }) discreteBar: OChartComponent;
  protected data: Array<Object>;
  protected yAxis: string = "SUM_AMOUNT";
  protected xAxis: string = "DATE_SUM_AMOUNT";
  protected service: OntimizeService;
  public lang;
  protected d3Locale = this.d3LocaleService.getD3LocaleConfiguration();
  protected chartParameters: DiscreteBarChartConfiguration;

  constructor(
    protected injector: Injector,
    private d3LocaleService: D3LocaleService,
    private translateService: OTranslateService
  ) {
    this.service = this.injector.get(OntimizeService);
    this.translateService.onLanguageChanged.subscribe(() => {
      this.queryData();
      this.translateNoDataMessage();
    });
    this._confDiscreteBar();
    this.translateNoDataMessage();
    this.queryData();
  }
  protected configureService() {
    const conf = this.service.getDefaultServiceConfiguration("movements");
    this.service.configureService(conf);
  }

  queryData() {
    let service: OntimizeService = this.injector.get(OntimizeService);
    const filter = {
      MOV_MONTH: new Date().getMonth() + 1,
      MOV_YEAR: new Date().getFullYear(),
    };
    const columns = ["SUM_AMOUNT", "DATE_SUM_AMOUNT", "USER_"];
    this.configureService();
    service
      .query(filter, columns, "totalIncomesAmountDay")
      .subscribe((resp) => {
        if (resp.code === 0) {
          this.adaptResult(resp.data);
          this.formater();
        } else {
          alert("Impossible to query data!");
        }
      });
  }
  adaptResult(data: any) {
    if (data && data.length) {
      let values = this.processValues(data);
      this.data = [
        {
          key: "Discrete serie",
          values: values,
        },
      ];
    }
  }
  processValues(data: Array<Object>): Array<Object> {
    const d3Locale = this.d3LocaleService.getD3LocaleConfiguration();
    var self = this;
    const format_x = (d) => {
      let date = new Date(d);
      const format =
        D3Locales[this.translateService.getCurrentLang().toUpperCase()]["date"];
      return d3Locale.timeFormat(format)(date);
    };

    return data.map((item: any) => {
      return {
        x: format_x(item[self.xAxis]),
        y: item[self.yAxis],
      };
    });
  }

  ngOnInit() {}

  public formater() {
    const chartService = this.discreteBar.getChartService();
    const chartOps = chartService.getChartOptions();
    chartOps["yAxis"]["tickFormat"] = (d) => {
      return d.toLocaleString("es-ES", { style: "currency", currency: "EUR" });
    };
  }
  public translateNoDataMessage() {
    this.lang = this.translateService.getCurrentLang().toUpperCase();

    if (this.lang === "ES") {
      this.chartParameters.noDataMessage = "No hay datos disponibles";
    } else if (this.lang === "EN") {
      this.chartParameters.noDataMessage = "No data available";
    }
  }
  public _confDiscreteBar() {
    this.chartParameters = new DiscreteBarChartConfiguration();
    this.chartParameters.margin.left = 80;
  }
}
