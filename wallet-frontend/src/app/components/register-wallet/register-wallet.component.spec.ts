import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterWalletComponent } from './register-wallet.component';

describe('RegisterWalletComponent', () => {
  let component: RegisterWalletComponent;
  let fixture: ComponentFixture<RegisterWalletComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterWalletComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterWalletComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
