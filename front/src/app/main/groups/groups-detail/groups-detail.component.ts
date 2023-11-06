import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-groups-detail",
  templateUrl: "./groups-detail.component.html",
  styleUrls: ["./groups-detail.component.css"],
})
export class GroupsDetailComponent implements OnInit {
  constructor() {}

  ngOnInit() {}
  getAmountColor(amount: number): string {
    if (amount > 0) {
      return "green";
    } else if (amount < 0) {
      return "red";
    } else {
      console.log({ amount });
      return "black";
    }
  }
}
