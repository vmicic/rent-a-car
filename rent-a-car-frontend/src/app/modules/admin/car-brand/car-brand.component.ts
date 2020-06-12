import { Component, OnInit, ViewChild, Renderer, TemplateRef } from '@angular/core';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { CarBrandService } from '../services/car-brand.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-car-brand',
  templateUrl: './car-brand.component.html',
  styleUrls: ['./car-brand.component.css']
})
export class CarBrandComponent implements OnInit {

  brands: any[] = [];
  brandsObs$: Subject<any[]> = new Subject<any[]>();

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

  newBrandForm: FormGroup;
  editBrandForm: FormGroup;

  constructor(
    private carBrandService: CarBrandService,
    private renderer: Renderer,
    private modalService: NgbModal,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.newBrandForm = this.formBuilder.group({
      name: ['', Validators.required]
    })

    this.editBrandForm = this.formBuilder.group({
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

    this.carBrandService.getAll().subscribe(
      response => {
        this.brands = response.body;
        this.dtTrigger.next();
      }
    );



  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("edit-clicked-id")) {
        let id: number = event.target.getAttribute("edit-clicked-id");

        let oneClass = this.brands.filter(singleClass => singleClass.id == id);

        this.editBrandForm.controls["id"].setValue(oneClass[0].id);
        this.editBrandForm.controls["name"].setValue(oneClass[0].name);
        this.openModalEdit();
      }

      if (event.target.hasAttribute("delete-clicked-id")) {
        let id = event.target.getAttribute("delete-clicked-id");

        this.carBrandService.deleteCarBrand(id).subscribe(
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
    this.carBrandService.createCarBrand(this.newBrandForm.value).subscribe(
      response => {
        this.modalService.dismissAll();
        location.reload();
      }
    )
  }

  editClass() {
    var myObj = new Object;
    myObj["name"] = this.editBrandForm.controls["name"].value;

    this.carBrandService.editBrand(this.editBrandForm.controls["id"].value, myObj).subscribe(
      response => {
        this.modalService.dismissAll();
        location.reload();
      }
    )
  }

}
