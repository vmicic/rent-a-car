import { Component, OnInit, ViewChild, Renderer } from '@angular/core';
import { RatingService } from '../../services/rating.service';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';

@Component({
  selector: 'app-show-all-ratings',
  templateUrl: './show-all-ratings.component.html',
  styleUrls: ['./show-all-ratings.component.css']
})
export class ShowAllRatingsComponent implements OnInit {

  ratings: any[] = [];
  ratingsObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  constructor(
    private renderer: Renderer,
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
        title: 'Rating',
        data: 'rating'
      }, {
        title: 'Comment',
        data: 'comment'
      }, {
        title: 'User posted'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="btn btn-success btn-small" title="Approve rating" approved-clicked-id="' + full.id + '"><img src="../../../../../assets/images/check.svg" approved-clicked-id="' + full.id + '" title="Approve rating"></button><button class="btn btn-danger btn-small ml-2" title="Reject rating" reject-clicked-id="' + full.id + '"><img src="../../../../../assets/images/x.svg" reject-clicked-id="' + full.id + '" title="Reject rating"></button>';
        }
      }
      ]
    }



    this.ratingService.getAllRatingsForApproval().subscribe(
      response => {
        this.ratings = response.body;
        this.dtTrigger.next();
      }
    )
  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("approved-clicked-id")) {
        let id: number = event.target.getAttribute("approved-clicked-id");

        this.ratingService.approveRating(id).subscribe(
          response => {
            window.location.reload();
          }
        )
      } else if(event.target.hasAttribute("reject-clicked-id")) {
        let id: number = event.target.getAttribute("reject-clicked-id");

        this.ratingService.rejectRating(id).subscribe(
          response => {
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
