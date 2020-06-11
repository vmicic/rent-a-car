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

  
  @ViewChild("edit", { static: true }) modalContent2: TemplateRef<any>;

  newClass: FormGroup;
  editClassForm: FormGroup;

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

    this.editClassForm = this.formBuilder.group({
      id: ['', Validators.required],
      name: ['', Validators.required]
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
        title: 'Name',
        data: 'name'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-warning btn-sm" title="Edit" edit-clicked-id="' + full.id + '"><img src="../../../../../assets/images/pencil.svg" edit-clicked-id="' + full.id + '" title="Edit"></button> <button class="waves-effect btn btn-danger btn-sm" title="Delete" delete-clicked-id="' + full.id + '"><img src="../../../../../assets/images/archive.svg" delete-clicked-id="' + full.id + '" title="Delete"></button>';
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
      if (event.target.hasAttribute("edit-clicked-id")) {
        let id: number = event.target.getAttribute("edit-clicked-id");

        console.log("Clicked id is: " + id);
        console.log(this.classes[0].id);

        let oneClass = this.classes.filter(singleClass => singleClass.id == id);
        console.log(oneClass);

        this.editClassForm.controls["id"].setValue(oneClass[0].id);
        this.editClassForm.controls["name"].setValue(oneClass[0].name);
        this.openModalEdit();
      }

      if (event.target.hasAttribute("delete-clicked-id")) {
        let id = event.target.getAttribute("delete-clicked-id");

        this.carClassService.deleteCarClass(id).subscribe(
          response => {
            location.reload();
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

  openModalEdit() {
    this.modalService.open(this.modalContent2, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReasonEdit(reason)}`;
    })
  }

  private getDismissReasonEdit(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
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
        location.reload();
      }
    )
  }

  editClass() {
    var myObj = new Object;
    myObj["name"] = this.editClassForm.controls["name"].value;

    this.carClassService.editClass(this.editClassForm.controls["id"].value, myObj).subscribe(
      response => {
        this.modalService.dismissAll();
        location.reload();
      }
    )
  }

}
