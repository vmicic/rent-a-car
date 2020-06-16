import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisementsNewComponent } from './advertisements-new.component';

describe('AdvertisementsNewComponent', () => {
  let component: AdvertisementsNewComponent;
  let fixture: ComponentFixture<AdvertisementsNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvertisementsNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvertisementsNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
