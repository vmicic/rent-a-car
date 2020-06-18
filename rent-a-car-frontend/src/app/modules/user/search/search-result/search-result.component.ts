import { Component, OnInit, ViewChild, Renderer } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SearchService } from '../../services/search.service';
import { HttpParams } from '@angular/common/http';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  pickup: any;
  fromDate: string;
  toDate: string;

  cars: any[] = [];
  carsObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  cart: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private searchService: SearchService,
    private renderer: Renderer
  ) {
    this.route.queryParams.subscribe(params => {
      this.pickup = params['pickup'];
      this.fromDate = params['fromDate'];
      this.toDate = params['toDate'];
    });
  }

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
          return '<button class="btn btn-success btn-small" title="Add to cart" clicked-id="' + full.id + '"><img src="../../../../../assets/images/cart.svg" clicked-id="' + full.id + '" title="Add to cart"></button>';
        }
      }
      ]
    }

    if(localStorage.getItem('cartRentACar') != null) {
      this.cart = JSON.parse(localStorage.getItem('cart'));
    }


    let params = {
      pickup: this.pickup,
      dateFrom: this.fromDate,
      dateTo: this.toDate
    };

    this.searchService.getSearchResult(params).subscribe(
      response => {
        this.cars = response.body;
        this.dtTrigger.next();
      }
    )
  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("clicked-id")) {
        let id: number = event.target.getAttribute("clicked-id");

        if(this.cart.indexOf(this.cars[id]) == -1) {
          this.cart.push(this.cars[id]);
          localStorage.setItem('cartRentACar', JSON.stringify(this.cart));
        }

        console.log(this.cart);
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
