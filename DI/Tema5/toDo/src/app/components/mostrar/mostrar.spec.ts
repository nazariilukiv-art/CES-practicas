import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Mostrar } from './mostrar';

describe('Mostrar', () => {
  let component: Mostrar;
  let fixture: ComponentFixture<Mostrar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Mostrar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Mostrar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
