import { Component, Injector, OnInit } from "@angular/core";
import { OTranslateService, OntimizeService } from "ontimize-web-ngx";
import { ChartSeries, PieChartConfiguration } from "ontimize-web-ngx-charts";

@Component({
  selector: "app-goals-home",
  templateUrl: "./goals-home.component.html",
  styleUrls: ["./goals-home.component.css"],
})
export class GoalsHomeComponent implements OnInit {
  protected chartParameters: PieChartConfiguration;
  public lang;
  protected service: OntimizeService;
  constructor(
    private translateService: OTranslateService,
    protected injector: Injector
  ) {
    this.service = this.injector.get(OntimizeService);
    this.translateService.onLanguageChanged.subscribe(() => {
      this.translateNoDataMessage();
    });
    this._confDiscreteBar();
    this.translateNoDataMessage();
  }

  ngOnInit() {}
  public translateNoDataMessage() {
    this.lang = this.translateService.getCurrentLang().toUpperCase();

    if (this.lang === "ES") {
      this.chartParameters.noDataMessage = "No hay datos disponibles";
    } else if (this.lang === "EN") {
      this.chartParameters.noDataMessage = "No data available";
    }
  }
  public _confDiscreteBar() {
    this.chartParameters = new PieChartConfiguration();
    this.chartParameters.labelType = "percent";
    this.chartParameters.legendPosition = "bottom";
  }
}
