import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, FormArray } from '@angular/forms';
import { PickupService } from '../../services/pickup.service';

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
    private pickupService: PickupService
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
  }

}
