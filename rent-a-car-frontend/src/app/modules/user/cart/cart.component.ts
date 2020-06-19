import { Component, OnInit, Renderer, ViewChild } from '@angular/core';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cars: any[] = [];
  carsObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  cart: any[] = [];

  reservationForm: FormGroup;

  constructor(
    private renderer: Renderer,
    private formBuilder: FormBuilder,
    private reservationService: ReservationService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.reservationForm = this.formBuilder.group({
      sameUser: ['', Validators.required]
    })

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      searching: true,
      rowId: 'id',
      columns: [{
        title: 'ID',
        data: 'id',
        visible: false
      }, {
        title: 'Brand',
        data: 'carBrand'
      }, {
        title: 'Model',
        data: 'carModel.name'
      }, {
        title: 'Class',
        data: 'carClass.name'
      }, {
        title: 'Fuel type',
        data: 'fuelType.name'
      }, {
        title: 'Transmission Type',
        data: 'transmissionType.name'
      }, {
        title: 'Seats for kids',
        data: 'seatsForKids'
      }, {
        title: 'Owner',
        data: 'user'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="btn btn-danger btn-small" title="Remove" clicked-id="' + full.id + '"><img src="../../../../../assets/images/archive.svg" clicked-id="' + full.id + '" title="Remove"></button>';
        }
      }
      ]
    }

    if(localStorage.getItem('cartRentACar') != null) {
      this.cart = JSON.parse(localStorage.getItem('cartRentACar'));
      this.cars = this.cart;
      this.dtTrigger.next();
    }
    

  }

  ngAfterViewInit(): void {
    this.dtTrigger.next();

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("clicked-id")) {
        let id: number = event.target.getAttribute("clicked-id");

        this.cars = this.cars.filter(car => car.id != id);
        localStorage.setItem('cartRentACar', JSON.stringify(this.cars));
      }
    });

    setTimeout(() => {
      this.datatableElement.dtInstance.then((dtInstance: DataTables.Api) => {
        dtInstance.columns().every(function () {
          const that = this;
          $('input', this.footer()).on('keyup change', function () {
            if (that.search() !== this['value']) {
              that
                .search(this['value'])
                .draw();
            }
          });
        });
      });
    }, 1000);
  }

  clearCart() : void {
    console.log('clearing cart');
    localStorage.removeItem('cartRentACar');
  }

  onSubmit() {
    if((this.reservationForm.controls["sameUser"].value)) {
      console.log('true')
    } else {
      console.log('false');
      for(let i = 0; i < this.cars.length; i++) {
        var reservation = new Object();
        reservation["carIds"] = [this.cars[i].id];
        reservation["fromDate"] = this.cars[i].fromDate.replace('T', ' ');
        reservation["toDate"] = this.cars[i].toDate.replace('T', ' ');
        console.log(reservation);

        this.reservationService.createReservation(reservation).subscribe(
          response => {
            console.log(response);
            this.router.navigate(["home"]);
          }
        )
      }
    }

  }

}
