import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { PickupService } from '../../services/pickup.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { Advertisement } from 'src/app/shared/domain/advertisement';
import { AdvertisementService } from '../../services/advertisement.service';

@Component({
  selector: 'app-advertisements-new',
  templateUrl: './advertisements-new.component.html',
  styleUrls: ['./advertisements-new.component.css']
})
export class AdvertisementsNewComponent implements OnInit {

  newAdvertisementForm: FormGroup;

  pickups: any[] = [];

  carId: any;

  constructor(
    private formBuilder: FormBuilder,
    private pickupService: PickupService,
    private route: ActivatedRoute,
    private advertisementService: AdvertisementService,
    private router: Router
  ) { }

  ngOnInit() {
    this.newAdvertisementForm = this.formBuilder.group({
      fromDate: ['', Validators.required],
      fromTime: ['', Validators.required],
      toDate: ['', Validators.required],
      toTime: ['', Validators.required],
      pickups: new FormArray([])
    })

    this.carId = this.route.snapshot.paramMap.get('id');

    this.pickupService.getAll().subscribe(
      response => {
        this.pickups = response.body;
        this.pickups.forEach((o, i) => {
          const control = new FormControl();
          (this.newAdvertisementForm.controls.pickups as FormArray).push(control);
        })
      }
    );

  }

  onSubmit() {
    const selectedPickupSpotsIds = this.newAdvertisementForm.value.pickups
      .map((v, i) => (v ? this.pickups[i].id : null))
      .filter(v => v !== null);
    console.log(selectedPickupSpotsIds);

    let dateHoursFrom = this.newAdvertisementForm.controls["fromTime"].value.hour;
    let dateHoursFromFormatted = ("0" + dateHoursFrom).slice(-2);

    let dateMinutesFrom = this.newAdvertisementForm.controls["fromTime"].value.minute;
    let dateMinutesFromFormatted = ("0" + dateMinutesFrom).slice(-2);

    let monthFrom = this.newAdvertisementForm.controls["fromDate"].value.month;
    let monthFromFormatted = ("0" + monthFrom).slice(-2);

    let dayFrom = this.newAdvertisementForm.controls["fromDate"].value.day;
    let dayFromFormatted = ("0" + dayFrom).slice(-2);

    let dateStringFrom = this.newAdvertisementForm.controls["fromDate"].value.year + '-' + monthFromFormatted +
    '-' + dayFromFormatted + ' ' + dateHoursFromFormatted + ':' + dateMinutesFromFormatted;

    let dateHoursTo = this.newAdvertisementForm.controls["toTime"].value.hour;
    let dateHoursToFormatted = ("0" + dateHoursTo).slice(-2);

    let dateMinutesTo = this.newAdvertisementForm.controls["toTime"].value.minute;
    let dateMinutesToFormatted = ("0" + dateMinutesTo).slice(-2);


    let monthTo = this.newAdvertisementForm.controls["toDate"].value.month;
    let monthToFormatted = ("0" + monthTo).slice(-2);

    let dayTo = this.newAdvertisementForm.controls["toDate"].value.day;
    let dayToFormatted = ("0" + dayTo).slice(-2);

    let dateStringTo = this.newAdvertisementForm.controls["toDate"].value.year + '-' + monthToFormatted +
    '-' + dayToFormatted + ' ' + dateHoursToFormatted + ':' + dateMinutesToFormatted;

    console.log(dateStringFrom);
    console.log(dateStringTo);

    const ad: Advertisement = <Advertisement> {
      fromDate: dateStringFrom,
      toDate: dateStringTo,
      pickupSpots: selectedPickupSpotsIds,
      carId: this.carId
    }

    console.log(ad);

    this.advertisementService.createAdvertisement(ad).subscribe(
      response => {
        console.log(response);
        this.router.navigate(['home/cars']);
      }
    )
  }

}
