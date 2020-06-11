import { Component, OnInit, ViewChild, Renderer, TemplateRef } from '@angular/core';
import { CarClassService } from '../services/car-class.service';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-car-class',
  templateUrl: './car-class.component.html',
  styleUrls: ['./car-class.component.css']
})
export class CarClassComponent implements OnInit {

  classes: any[] = [];
  classesObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  //modal setting

  @ViewChild("content", { static: true }) modalContent: TemplateRef<any>;
  closeResult: string;
  errorMessage: string;

  newClass: FormGroup;

  constructor(
    private carClassService: CarClassService,
    private renderer: Renderer,
    private modalService: NgbModal,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.newClass = this.formBuilder.group({
      name: ['', Validators.required]
    })

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      searching: true,
      rowId: 'id',
      columns: [{
        title: 'ID',
        data: 'id'
      }, {
        title: 'Name',
        data: 'name'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-secondary btn-sm" title="Buy item" clicked-id="' + full.id + '"></button> ';
        }
      }
      ]

    }


    this.carClassService.getAll().subscribe(
      response => {
        this.classes = response.body;
        this.dtTrigger.next();
      }
    );



  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("schedule-id")) {
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

  createClass() {
    this.carClassService.createCarClass(this.newClass.value).subscribe(
      response => {
        this.modalService.dismissAll();
        console.log(response);
        location.reload();
      }
    )
  }
}
