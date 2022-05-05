import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { PaimentFactureService } from '../service/paiment-facture.service';

import { PaimentFactureComponent } from './paiment-facture.component';

describe('PaimentFacture Management Component', () => {
  let comp: PaimentFactureComponent;
  let fixture: ComponentFixture<PaimentFactureComponent>;
  let service: PaimentFactureService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [PaimentFactureComponent],
    })
      .overrideTemplate(PaimentFactureComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PaimentFactureComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(PaimentFactureService);

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
    expect(comp.paimentFactures?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
