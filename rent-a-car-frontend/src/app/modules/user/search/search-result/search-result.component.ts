import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SearchService } from '../../services/search.service';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  pickup: any;
  fromDate: string;
  toDate: string;

  constructor(
    private route: ActivatedRoute,
    private searchService: SearchService
  ) {
    this.route.queryParams.subscribe(params => {
      this.pickup = params['pickup'];
      this.fromDate = params['fromDate'];
      this.toDate = params['toDate'];
  });
   }

  ngOnInit() {
    console.log(this.fromDate);

    let params = {
      pickup: this.pickup,
      dateFrom: this.fromDate,
      dateTo: this.toDate
    };

    this.searchService.getSearchResult(params).subscribe(
      response => {
        console.log(response);
      }
    )
  }

}
