import { Component, Injector, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { OntimizeService } from "ontimize-web-ngx";
import { ExpensesNewComponent } from "../expenses/expenses-new/expenses-new.component";

@Component({
  selector: "home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent implements OnInit {
  protected service: OntimizeService;
  public balance: number;
  public expenseBalance: number;
  public incomeBalance: number;
  public MONTHLY_BALANCE: string = "MONTHLY_BALANCE";
  servicePath = "/balances";
  httpOptions: any;
  constructor(
    private router: Router,
    private actRoute: ActivatedRoute,
    protected injector: Injector,
    public dialog: MatDialog
  ) {
    this.service = this.injector.get(OntimizeService);
  }

  ngOnInit() {
    this.queryBalance();
    this.queryExpenseBalance();
    this.queryIncomeBalance();
    console.log(this.expenseBalance);
  }

  queryBalance() {
    const filter = {};
    const columns = ["user_", "balance"];
    this.service.query(filter, columns, "balance").subscribe((resp) => {
      if (resp.code === 0) {
        this.getBalance(resp.data);
      }
    });
  }
  queryExpenseBalance() {
    const filter = {};
    const columns = ["user_", "expensebalance"];
    this.service.query(filter, columns, "expensebalance").subscribe((resp) => {
      if (resp.code === 0) {
        this.getExpenseBalance(resp.data);
      }
    });
  }
  queryIncomeBalance() {
    const filter = {};
    const columns = ["user_", "incomebalance"];
    this.service.query(filter, columns, "incomebalance").subscribe((resp) => {
      if (resp.code === 0) {
        this.getIncomeBalance(resp.data);
      }
    });
  }
  getBalance(data: { balance: number }[]) {
    console.log(data);
    this.balance = data[0].balance;
    console.log(this.balance);
  }
  getExpenseBalance(data: { expensebalance: number }[]) {
    console.log(data);
    this.expenseBalance = data[0].expensebalance;
    console.log(this.expenseBalance);
  }
  getIncomeBalance(data: { incomebalance: number }[]) {
    console.log(data);
    this.incomeBalance = data[0].incomebalance;
  }

  protected configureService() {
    const conf = this.service.getDefaultServiceConfiguration("movements");
    this.service.configureService(conf);
  }

  navigate() {
    this.router.navigate(["../", "login"], { relativeTo: this.actRoute });
  }

  buttonExpenses() {
    this.dialog.open(ExpensesNewComponent, {
      width: "fit-content",
      height: "580px",
    });
    /*  this.router.navigate(["../../expenses", "new"], {
      relativeTo: this.actRoute,
    });*/
  }
  buttonIncomes() {
    this.router.navigate(["../../incomes", "new"], {
      relativeTo: this.actRoute,
    });
  }
}
