import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { VirementService } from '../service/virement.service';

import { VirementComponent } from './virement.component';

describe('Virement Management Component', () => {
  let comp: VirementComponent;
  let fixture: ComponentFixture<VirementComponent>;
  let service: VirementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [VirementComponent],
    })
      .overrideTemplate(VirementComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VirementComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(VirementService);

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
    expect(comp.virements?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
