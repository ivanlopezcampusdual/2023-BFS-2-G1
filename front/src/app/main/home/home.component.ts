import { Component, Injector, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { OntimizeService } from "ontimize-web-ngx";
import { ExpensesNewComponent } from "../expenses/expenses-new/expenses-new.component";
import { IncomesNewComponent } from "../incomes/incomes-new/incomes-new.component";

@Component({
  selector: "home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent implements OnInit {
  protected service: OntimizeService;
  public balance: number = 0;
  public expenseBalance: number = 0;
  public incomeBalance: number = 0;

  public MONTHLY_BALANCE: string = "MONTHLY_BALANCE";
  public TOTAL_BALANCE: string = "TOTAL_BALANCE";
  public TOTAL_EXPENSE: string = "TOTAL_EXPENSE";
  public TOTAL_INCOME: string = "TOTAL_INCOME";

  public user: string;

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
  }


  queryBalance() {
    const filter = {
      MONTH: new Date().getMonth() + 1,
      YEAR: new Date().getFullYear(),
    };
    const columns = ["user_", "balance"];
    this.service.query(filter, columns, "balance").subscribe((resp) => {
      if (resp.code === 0) {
        this.getBalance(resp.data);
      }
    });
  }
  queryExpenseBalance() {
    const filter = {};
    const columns = ["user_", "expenseBalance"];
    this.service
      .query(filter, columns, "totalExpensesForCurrentMonth")
      .subscribe((resp) => {
        if (resp.code === 0) {
          this.getExpenseBalance(resp.data);
        }
      });
  }
  queryIncomeBalance() {
    const filter = {};
    const columns = ["user_", "incomeBalance"];
    this.service
      .query(filter, columns, "totalIncomesForCurrentMonth")
      .subscribe((resp) => {
        if (resp.code === 0) {
          this.getIncomeBalance(resp.data);
        }
      });
  }
  getBalance(data: { balance: number }[]) {
    this.balance = data[0].balance || 0;
  }
  getExpenseBalance(data: { expenseBalance: number }[]) {
    this.expenseBalance = data[0].expenseBalance || 0;
  }

  getIncomeBalance(data: { incomeBalance: number }[]) {
    this.incomeBalance = data[0].incomeBalance || 0;
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
      data: { showCloseButton: true },
      width: "fit-content",
      height: "580px",
      closeOnNavigation: false,
    });
  }
  buttonIncomes() {
    this.dialog.open(IncomesNewComponent, {
      data: { showCloseButton: true },
      width: "fit-content",
      height: "580px",
      closeOnNavigation: false,
    });
  }
}
