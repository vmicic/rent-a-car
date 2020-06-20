import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllRatingsComponent } from './show-all-ratings.component';

describe('ShowAllRatingsComponent', () => {
  let component: ShowAllRatingsComponent;
  let fixture: ComponentFixture<ShowAllRatingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllRatingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllRatingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
