import { Component, OnInit, Renderer, ViewChild, TemplateRef } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { ReservationService } from '../../services/reservation.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from '../../services/message.service';
import { RatingService } from 'src/app/modules/admin/services/rating.service';

@Component({
  selector: 'app-requested',
  templateUrl: './requested.component.html',
  styleUrls: ['./requested.component.css']
})
export class RequestedComponent implements OnInit {

  notification: string;

  reservations: any[] = [];
  reservationsObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  newMessageForm: FormGroup;
  usernameSelected: any;

  @ViewChild("content", { static: true }) modalContent: TemplateRef<any>;
  closeResult: string;
  errorMessage: string;

  
  @ViewChild("edit", { static: true }) modalContent2: TemplateRef<any>;

  newRatingForm: FormGroup;

  carIdsToRate: any[] = [];

  constructor(
    private renderer: Renderer,
    private reservationService: ReservationService,
    private formBuilder: FormBuilder,
    private modalService: NgbModal,
    private messageService: MessageService,
    private ratingService: RatingService
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
        title: 'Reservation state',
        data: 'reservationState'
      },
      {
        title: 'Car ids'
      }, {
        title: 'Owner'
      },{
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="btn btn-info btn-small" title="Send message" message-clicked-id="' + full.id + '"><img src="../../../../../assets/images/chat.svg" message-clicked-id="' + full.id + '" title="Send message"></button><button class="btn btn-warning btn-small ml-2" title="Rate car" rate-clicked-id="' + full.id + '"><img src="../../../../../assets/images/star-half.svg" rate-clicked-id="' + full.id + '" title="Rate car"></button>';
        }
      }
      ]
    }

    this.newRatingForm = this.formBuilder.group({
      carId: ['', Validators.required],
      rating: ['', Validators.required],
      comment: ['', Validators.required]
    })

    this.newMessageForm = this.formBuilder.group({
      usernameReceiver: ['', Validators.required],
      content: ['', Validators.required]
    })



    this.reservationService.getReservationsRequested().subscribe(
      response => {
        this.reservations = response.body;
        this.dtTrigger.next();
      }
    )
  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("message-clicked-id")) {
        let id: number = event.target.getAttribute("message-clicked-id");
        let reservation = this.reservations.filter(reservation => reservation.id == id);
        this.usernameSelected = reservation[0].userOwnerCar.email;

        this.newMessageForm.controls["usernameReceiver"].setValue(this.usernameSelected);
        this.openModal();

      } else if(event.target.hasAttribute("rate-clicked-id")) {
        let id: number = event.target.getAttribute("rate-clicked-id");

        let reservation = this.reservations.filter(reservation => reservation.id == id);
        
        for(let i = 0; i < reservation[0].cars.length; i++) {
          this.carIdsToRate.push(reservation[0].cars[i].id);
        }

        this.openModalEdit();

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

  sendMessage() {
    console.log(this.newMessageForm.value);
    this.messageService.sendMessage(this.newMessageForm.value).subscribe(
      response => {
        console.log(response);
      },
      error => {
        this.notification = error.error;
      }
    )
  }

  openModal() {
    this.modalService.open(this.modalContent, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    })
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  openModalEdit() {
    this.modalService.open(this.modalContent2, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    })
  }

  submitRating() {
    this.ratingService.createRating(this.newRatingForm.value).subscribe(
      response => {
        console.log(response);
      }
    )
  }

}
