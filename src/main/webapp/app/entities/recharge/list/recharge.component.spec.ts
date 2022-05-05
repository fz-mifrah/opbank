import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { RechargeService } from '../service/recharge.service';

import { RechargeComponent } from './recharge.component';

describe('Recharge Management Component', () => {
  let comp: RechargeComponent;
  let fixture: ComponentFixture<RechargeComponent>;
  let service: RechargeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [RechargeComponent],
    })
      .overrideTemplate(RechargeComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RechargeComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(RechargeService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.recharges?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
