import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CarBrandService } from 'src/app/modules/admin/services/car-brand.service';
import { CarModelService } from 'src/app/modules/admin/services/car-model.service';
import { CarClassService } from 'src/app/modules/admin/services/car-class.service';
import { FuelTypeService } from 'src/app/modules/admin/services/fuel-type.service';
import { TransmissionTypeService } from 'src/app/modules/admin/services/transmission-type.service';
import { CarsService } from '../cars.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cars-new',
  templateUrl: './cars-new.component.html',
  styleUrls: ['./cars-new.component.css']
})
export class CarsNewComponent implements OnInit {

  newCarForm: FormGroup;

  carBrands: any[] = [];
  carModels: any[] = [];
  carModelsFiltered: any[] = [];
  carClasses: any[] = [];
  fuelTypes: any[] = [];
  transmissionTypes: any[] = [];

  images: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private carBrandService: CarBrandService,
    private carModelService: CarModelService,
    private carClassService: CarClassService,
    private FuelTypeService: FuelTypeService,
    private TransmissionTypeService: TransmissionTypeService,
    private carService: CarsService,
    private router: Router
  ) { }

  ngOnInit() {
    this.newCarForm = this.formBuilder.group({
      carBrandId: ['', Validators.required],
      carModelId: ['', Validators.required],
      carClassId: ['', Validators.required],
      fuelTypeId: ['', Validators.required],
      transmissionTypeId: ['', Validators.required],
      seatsForKids: ['', Validators.required]
    })

    this.carBrandService.getAll().subscribe(
      response => {
        this.carBrands = response.body;
      }
    )

    this.carModelService.getAll().subscribe(
      response => {
        this.carModels = response.body;
      }
    )
    this.carClassService.getAll().subscribe(
      response => {
        this.carClasses = response.body;
      }
    )

    this.FuelTypeService.getAll().subscribe(
      response => {
        this.fuelTypes = response.body;
      }
    )
    this.TransmissionTypeService.getAll().subscribe(
      response => {
        this.transmissionTypes = response.body;
      }
    )
  }

  changeModels(selectedObj) {
    let id = this.newCarForm.controls["carBrandId"].value;
    this.carModelsFiltered = this.carModels.filter(carModel => carModel.carBrand.id == id);
    for (let i = 0; i < this.carModels.length; i++) {
    }
  }

  onSubmit() {
    this.carService.addCar(this.newCarForm.value).subscribe(
      response => {
        let id = response.body.id;
        for (let i = 0; i < this.images.length; i++) {
          const formData = new FormData();
          formData.append('imageFile', this.images[i]);
          formData.append('carId', id);

          this.carService.sendImage(formData).subscribe(
            response => {
            }
          )
        }
      }
    )
  }

  onFileChanges(event) {
    this.images = event.target['files'];
  }



}
