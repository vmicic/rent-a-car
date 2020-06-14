import { Component, OnInit, ViewChild, TemplateRef, Renderer } from '@angular/core';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: any[] = [];
  usersObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  //modal setting

  constructor(
    private usersService: UsersService,
    private renderer: Renderer
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
        title: 'Email',
        data: 'email'
      },{
        title: 'First Name',
        data: 'firstName'
      },{
        title: 'Last Name',
        data: 'lastname'
      },{
        title: 'Enabled',
        data: 'enabled'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-success btn-sm" title="Activate user" activate-clicked-id="' + full.id + '"><img src="../../../../../assets/images/check.svg" activate-clicked-id="' + full.id + '" title="Activate user"></button><button class="waves-effect btn btn-warning btn-sm" title="Deactivate user" deactivate-clicked-id="' + full.id + '"><img src="../../../../../assets/images/deactivate.svg" deactivate-clicked-id="' + full.id + '" title="Deactivate user"></button> <button class="waves-effect btn btn-danger btn-sm" title="Delete user" delete-clicked-id="' + full.id + '"><img src="../../../../../assets/images/archive.svg" delete-clicked-id="' + full.id + '" title="Delete user"></button>';
        }
      }
      ]

    }

    this.usersService.getAll().subscribe(
      response => {
        this.users = response.body;
        this.dtTrigger.next();
      }
    );



  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("activate-clicked-id")) {
        let id: number = event.target.getAttribute("activate-clicked-id");

        this.usersService.activate(id).subscribe(
          response => {
            location.reload();
          }
        )
      }

      if (event.target.hasAttribute("deactivate-clicked-id")) {
        let id: number = event.target.getAttribute("deactivate-clicked-id");

        this.usersService.deactivate(id).subscribe(
          response => {
            location.reload();
          }
        )
      }

      if (event.target.hasAttribute("delete-clicked-id")) {
        let id = event.target.getAttribute("delete-clicked-id");

        this.usersService.delete(id).subscribe(
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


}
