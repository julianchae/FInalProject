import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudFoodTruckComponent } from './crud-food-truck.component';

describe('CrudFoodTruckComponent', () => {
  let component: CrudFoodTruckComponent;
  let fixture: ComponentFixture<CrudFoodTruckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrudFoodTruckComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudFoodTruckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
