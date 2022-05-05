import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TransferService } from '../service/transfer.service';

import { TransferComponent } from './transfer.component';

describe('Transfer Management Component', () => {
  let comp: TransferComponent;
  let fixture: ComponentFixture<TransferComponent>;
  let service: TransferService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [TransferComponent],
    })
      .overrideTemplate(TransferComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TransferComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TransferService);

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
    expect(comp.transfers?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
