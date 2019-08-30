import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewflaggedtransactionsComponent } from './viewflaggedtransactions.component';

describe('ViewflaggedtransactionsComponent', () => {
  let component: ViewflaggedtransactionsComponent;
  let fixture: ComponentFixture<ViewflaggedtransactionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewflaggedtransactionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewflaggedtransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
