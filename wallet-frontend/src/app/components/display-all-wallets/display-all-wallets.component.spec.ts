import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllWalletsComponent } from './display-all-wallets.component';

describe('DisplayAllWalletsComponent', () => {
  let component: DisplayAllWalletsComponent;
  let fixture: ComponentFixture<DisplayAllWalletsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayAllWalletsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayAllWalletsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
