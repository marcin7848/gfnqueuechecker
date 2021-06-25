import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsServersComponent } from './settings-servers.component';

describe('SettingsServersComponent', () => {
  let component: SettingsServersComponent;
  let fixture: ComponentFixture<SettingsServersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsServersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsServersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
