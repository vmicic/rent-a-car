import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { CarsService } from '../../cars/cars.service';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-search-car-details',
  templateUrl: './search-car-details.component.html',
  styleUrls: ['./search-car-details.component.css']
})
export class SearchCarDetailsComponent implements OnInit {


  selectedCar$: Observable<any>;
  carId: string;

  images: any[] = [];

  imagesObs$: Observable<any>;

  constructor(
    private router: ActivatedRoute,
    private carService: CarsService
  ) { }

  ngOnInit() {
    this.selectedCar$ = this.router.paramMap.pipe(
      switchMap((params: ParamMap) => {
        this.carId = params.get('id');
        return this.carService.getCar(this.carId);
      })
    )

    this.imagesObs$ = this.router.paramMap.pipe(
      switchMap((params: ParamMap) => {
        this.carId = params.get('id');
        return this.carService.getImages(this.carId);
      })
    )


  }

}
