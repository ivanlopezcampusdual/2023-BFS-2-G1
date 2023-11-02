import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-expenses-home",
  templateUrl: "./expenses-home.component.html",
  styleUrls: ["./expenses-home.component.css"],
})
export class ExpensesHomeComponent implements OnInit {
  constructor(private route: ActivatedRoute) {}

  ngOnInit() {}
}
