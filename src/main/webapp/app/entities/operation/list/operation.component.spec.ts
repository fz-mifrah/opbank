import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { OperationService } from '../service/operation.service';

import { OperationComponent } from './operation.component';

describe('Operation Management Component', () => {
  let comp: OperationComponent;
  let fixture: ComponentFixture<OperationComponent>;
  let service: OperationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [OperationComponent],
    })
      .overrideTemplate(OperationComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OperationComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OperationService);

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
    expect(comp.operations?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
