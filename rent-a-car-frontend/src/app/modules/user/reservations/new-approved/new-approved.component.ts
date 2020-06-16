import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Reservation } from 'src/app/shared/domain/reservation';
import { ReservationService } from '../../services/reservation.service';

@Component({
  selector: 'app-new-approved',
  templateUrl: './new-approved.component.html',
  styleUrls: ['./new-approved.component.css']
})
export class NewApprovedComponent implements OnInit {

  newApprovedForm: FormGroup;
  carId: any;

  notification: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private reservationService: ReservationService
  ) { }

  ngOnInit() {
    this.newApprovedForm = this.formBuilder.group({
      fromDate: ['', Validators.required],
      fromTime: ['', Validators.required],
      toDate: ['', Validators.required],
      toTime: ['', Validators.required]
    })

    this.carId = this.route.snapshot.paramMap.get('id');
  }

  onSubmit() {

    let dateHoursFrom = this.newApprovedForm.controls["fromTime"].value.hour;
    let dateHoursFromFormatted = ("0" + dateHoursFrom).slice(-2);

    let dateMinutesFrom = this.newApprovedForm.controls["fromTime"].value.minute;
    let dateMinutesFromFormatted = ("0" + dateMinutesFrom).slice(-2);

    let monthFrom = this.newApprovedForm.controls["fromDate"].value.month;
    let monthFromFormatted = ("0" + monthFrom).slice(-2);

    let dayFrom = this.newApprovedForm.controls["fromDate"].value.day;
    let dayFromFormatted = ("0" + dayFrom).slice(-2);

    let dateStringFrom = this.newApprovedForm.controls["fromDate"].value.year + '-' + monthFromFormatted +
      '-' + dayFromFormatted + ' ' + dateHoursFromFormatted + ':' + dateMinutesFromFormatted;

    let dateHoursTo = this.newApprovedForm.controls["toTime"].value.hour;
    let dateHoursToFormatted = ("0" + dateHoursTo).slice(-2);

    let dateMinutesTo = this.newApprovedForm.controls["toTime"].value.minute;
    let dateMinutesToFormatted = ("0" + dateMinutesTo).slice(-2);


    let monthTo = this.newApprovedForm.controls["toDate"].value.month;
    let monthToFormatted = ("0" + monthTo).slice(-2);

    let dayTo = this.newApprovedForm.controls["toDate"].value.day;
    let dayToFormatted = ("0" + dayTo).slice(-2);

    let dateStringTo = this.newApprovedForm.controls["toDate"].value.year + '-' + monthToFormatted +
      '-' + dayToFormatted + ' ' + dateHoursToFormatted + ':' + dateMinutesToFormatted;

    const reservation: Reservation = <Reservation>{
      fromDate: dateStringFrom,
      toDate: dateStringTo,
      carId: this.carId
    }

    this.reservationService.createReservation(reservation).subscribe(
      response => {
        console.log(response);
        this.router.navigate(['home/cars']);
      },
      error => {
        this.notification = error.error;
        console.log(error);
        console.log(this.notification);
      }
    )
  }

}
