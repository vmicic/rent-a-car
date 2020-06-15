import { Component, OnInit, ViewChild, Renderer } from '@angular/core';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { FuelTypeService } from 'src/app/modules/admin/services/fuel-type.service';
import { FormBuilder } from '@angular/forms';
import { CarsService } from '../cars.service';

@Component({
  selector: 'app-cars-list',
  templateUrl: './cars-list.component.html',
  styleUrls: ['./cars-list.component.css']
})
export class CarsListComponent implements OnInit {

 
  cars: any[] = [];
  carsObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  //modal setting


  constructor(
    private carService: CarsService,
    private renderer: Renderer,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

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
      },{
        title: 'Model',
        data: 'carModel.name'
      },{
        title: 'Class',
        data: 'carClass.name'
      },{
        title: 'Fuel type',
        data: 'fuelType.name'
      },{
        title: 'Transmission Type',
        data: 'transmissionType.name'
      },{
        title: 'Seats for kids',
        data: 'seatsForKids'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button></button>'
        }
      }
      ]

    }

    this.carService.getAllCarsFromUser().subscribe(
      response => {
        this.cars = response.body;
        this.dtTrigger.next();
      }
    );



  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("edit-clicked-id")) {
        let id: number = event.target.getAttribute("edit-clicked-id");


      }

      if (event.target.hasAttribute("delete-clicked-id")) {
        let id = event.target.getAttribute("delete-clicked-id");

    
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

}
