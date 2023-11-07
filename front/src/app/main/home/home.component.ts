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
  public balance: number;
  public expenseBalance: number;
  public incomeBalance: number;
  public ruta = this.router.navigate(["/main/", "home"], {
    relativeTo: this.actRoute,
  });

  public MONTHLY_BALANCE: string = "MONTHLY_BALANCE";
  public TOTAL_BALANCE: string = "TOTAL_BALANCE";
  public TOTAL_EXPENSE: string = "TOTAL_EXPENSE";
  public TOTAL_INCOME: string = "TOTAL_INCOME";

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
    const filter = {'MONTH' : new Date().getMonth() + 1 , 'YEAR' : new Date().getFullYear()};
    const columns = ["user_", "balance" ];
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
    this.balance = 0 || data[0].balance;
  }
  getExpenseBalance(data: { expenseBalance: number }[]) {
    if (data[0] === undefined || data[0] === null) {
      this.expenseBalance = 0;
    } else {
      this.expenseBalance = data[0].expenseBalance;
    }
  }

  getIncomeBalance(data: { incomeBalance: number }[]) {
    if (data[0] === undefined || data[0] === null) {
      this.incomeBalance = 0;
    } else {
      this.incomeBalance = data[0].incomeBalance;
    }
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
      data: { showCloseButton: true, cancelUrl: this.ruta },
      width: "fit-content",
      height: "580px",
    });
  }
  buttonIncomes() {
    this.dialog.open(IncomesNewComponent, {
      data: { showCloseButton: true, cancelUrl: this.ruta },
      width: "fit-content",
      height: "580px",
    });
  }
}
