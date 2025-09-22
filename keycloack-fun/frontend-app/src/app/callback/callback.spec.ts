import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Callback } from './callback';

describe('Callback', () => {
  let component: Callback;
  let fixture: ComponentFixture<Callback>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Callback]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Callback);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
