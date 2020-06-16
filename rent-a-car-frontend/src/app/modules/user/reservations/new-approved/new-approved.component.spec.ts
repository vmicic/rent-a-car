import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewApprovedComponent } from './new-approved.component';

describe('NewApprovedComponent', () => {
  let component: NewApprovedComponent;
  let fixture: ComponentFixture<NewApprovedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewApprovedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewApprovedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
