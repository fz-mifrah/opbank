import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DestinatairDetailComponent } from './destinatair-detail.component';

describe('Destinatair Management Detail Component', () => {
  let comp: DestinatairDetailComponent;
  let fixture: ComponentFixture<DestinatairDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DestinatairDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ destinatair: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(DestinatairDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DestinatairDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load destinatair on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.destinatair).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
