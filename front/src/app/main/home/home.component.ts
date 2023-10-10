import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { IBalance } from "./Ibalance.service"; 
import { HttpHeaders } from '@angular/common/http';


@Component({
  selector: "home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent implements OnInit {
  public balance: any; 
  public MONTHLY_BALANCE:string = "MONTHLY_BALANCE"; 
  httpOptions:any; 
  constructor(private router: Router, private actRoute: ActivatedRoute, private balanceService: IBalance) {
  }


  ngOnInit() {
    this.balanceService.query({}, )
      .subscribe(
        arg => {
          console.log(arg.data[0].BALANCE); 
          this.balance = arg.data[0].BALANCE; 

        } 
        );
    
    
  }

  navigate() {
    this.router.navigate(["../", "login"], { relativeTo: this.actRoute });
  }

  buttonExpenses() {
    this.router.navigate(['/main/expenses/expenses-new']);
  }
  buttonIncomes(){
    this.router.navigate(['/main/incomes/incomes-new']);
  }

 
}
