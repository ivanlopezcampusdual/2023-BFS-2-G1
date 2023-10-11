import { Component, Injector, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { OntimizeService } from "ontimize-web-ngx";


@Component({
  selector: "home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent implements OnInit {
  protected service: OntimizeService;
  public balance: number; 
  public MONTHLY_BALANCE:string = "MONTHLY_BALANCE"; 
  servicePath='/users/balance'; 
  httpOptions:any; 
  constructor(private router: Router, private actRoute: ActivatedRoute,  protected injector: Injector) {
    this.service = this.injector.get(OntimizeService); 
  }

  ngOnInit() {
    const filter = {}; 
    const columns = ['user_', 'balance'];  
    this.configureService(); 
    this.service.query(filter, columns, 'balance').subscribe(resp=>{
      if(resp.code === 0){
        this.getBalance(resp.data); 
      }
      })
  }

  getBalance(data: { balance: number; }[]){
    this.balance = data[0].balance; 
  }

  protected configureService() {
    const conf = this.service.getDefaultServiceConfiguration('users');
    this.service.configureService(conf);
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
