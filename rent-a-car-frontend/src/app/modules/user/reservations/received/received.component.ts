import { Component, OnInit, ViewChild, Renderer } from '@angular/core';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { ReservationService } from '../../services/reservation.service';

@Component({
  selector: 'app-received',
  templateUrl: './received.component.html',
  styleUrls: ['./received.component.css']
})
export class ReceivedComponent implements OnInit {
  
  reservations: any[] = [];
  reservationsObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  constructor(
    private renderer: Renderer,
    private reservationService: ReservationService
  ) {
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
        title: 'From',
        data: 'fromDate'
      }, {
        title: 'To',
        data: 'toDate'
      }, {
        title: 'Car ids'
      },{
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="btn btn-success btn-small" title="Approve reservation" approved-clicked-id="' + full.id + '"><img src="../../../../../assets/images/check.svg" approved-clicked-id="' + full.id + '" title="Approve reservation"></button><button class="btn btn-danger btn-small ml-2" title="Reject reservation" reject-clicked-id="' + full.id + '"><img src="../../../../../assets/images/x.svg" reject-clicked-id="' + full.id + '" title="Reject reservation"></button>';
        }
      }
      ]
    }



    this.reservationService.getReservationsForApproval().subscribe(
      response => {
        this.reservations = response.body;
        this.dtTrigger.next();
      }
    )
  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("approved-clicked-id")) {
        let id: number = event.target.getAttribute("approved-clicked-id");

        this.reservationService.approveReservation(id).subscribe(
          response => {
            console.log(response);
            window.location.reload();
          }
        )
      } else if(event.target.hasAttribute("reject-clicked-id")) {
        let id: number = event.target.getAttribute("reject-clicked-id");

        this.reservationService.reject(id).subscribe(
          response => {
            console.log(response);
            window.location.reload();
          }
        )
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
