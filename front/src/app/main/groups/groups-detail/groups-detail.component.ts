import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-groups-detail",
  templateUrl: "./groups-detail.component.html",
  styleUrls: ["./groups-detail.component.css"],
})
export class GroupsDetailComponent implements OnInit {
  constructor() {}

  ngOnInit() {}
  getAmountColor(amount) {
    console.log({ amount });

    if (amount < 0) {
      return "red";
    } else {
      return "green";
    }
  }
}
