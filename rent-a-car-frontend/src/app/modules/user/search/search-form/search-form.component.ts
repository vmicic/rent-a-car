import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, FormArray } from '@angular/forms';
import { PickupService } from '../../services/pickup.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  searchForm: FormGroup

  pickups: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private pickupService: PickupService,
    private router: Router
  ) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      fromDate: ['', Validators.required],
      fromTime: ['', Validators.required],
      toDate: ['', Validators.required],
      toTime: ['', Validators.required],
      pickup: ['', Validators.required]
    })

    this.pickupService.getAll().subscribe(
      response => {
        this.pickups = response.body;
      }
    );
  }

  onSubmit() {
    console.log(this.searchForm.value);

    let dateHoursFrom = this.searchForm.controls["fromTime"].value.hour;
    let dateHoursFromFormatted = ("0" + dateHoursFrom).slice(-2);

    let dateMinutesFrom = this.searchForm.controls["fromTime"].value.minute;
    let dateMinutesFromFormatted = ("0" + dateMinutesFrom).slice(-2);

    let monthFrom = this.searchForm.controls["fromDate"].value.month;
    let monthFromFormatted = ("0" + monthFrom).slice(-2);

    let dayFrom = this.searchForm.controls["fromDate"].value.day;
    let dayFromFormatted = ("0" + dayFrom).slice(-2);

    let dateStringFrom = this.searchForm.controls["fromDate"].value.year + '-' + monthFromFormatted +
    '-' + dayFromFormatted + 'T' + dateHoursFromFormatted + ':' + dateMinutesFromFormatted;

    let dateHoursTo = this.searchForm.controls["toTime"].value.hour;
    let dateHoursToFormatted = ("0" + dateHoursTo).slice(-2);

    let dateMinutesTo = this.searchForm.controls["toTime"].value.minute;
    let dateMinutesToFormatted = ("0" + dateMinutesTo).slice(-2);


    let monthTo = this.searchForm.controls["toDate"].value.month;
    let monthToFormatted = ("0" + monthTo).slice(-2);

    let dayTo = this.searchForm.controls["toDate"].value.day;
    let dayToFormatted = ("0" + dayTo).slice(-2);

    let dateStringTo = this.searchForm.controls["toDate"].value.year + '-' + monthToFormatted +
    '-' + dayToFormatted + 'T' + dateHoursToFormatted + ':' + dateMinutesToFormatted;

    this.router.navigate(['/home/search/result'], {queryParams: {pickup : this.searchForm.controls["pickup"].value, fromDate: dateStringFrom, toDate: dateStringTo}});
  }

}
