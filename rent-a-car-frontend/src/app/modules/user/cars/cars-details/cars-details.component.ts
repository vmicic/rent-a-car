import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { CarsService } from '../cars.service';
import {switchMap} from 'rxjs/operators';
import { AuthService } from 'src/app/shared/auth/auth.service';

@Component({
  selector: 'app-cars-details',
  templateUrl: './cars-details.component.html',
  styleUrls: ['./cars-details.component.css']
})
export class CarsDetailsComponent implements OnInit {

  selectedCar$: Observable<any>;
  carId: string;

  images: any[] = [];

  imagesObs$: Observable<any>;

  username: string;

  constructor(
    private router: ActivatedRoute,
    private carService: CarsService,
    private authService: AuthService
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

    this.username = this.authService.getUsername();


  }

}
