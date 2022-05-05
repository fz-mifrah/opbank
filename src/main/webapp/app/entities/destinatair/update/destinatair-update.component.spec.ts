import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DestinatairService } from '../service/destinatair.service';
import { IDestinatair, Destinatair } from '../destinatair.model';

import { DestinatairUpdateComponent } from './destinatair-update.component';

describe('Destinatair Management Update Component', () => {
  let comp: DestinatairUpdateComponent;
  let fixture: ComponentFixture<DestinatairUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let destinatairService: DestinatairService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DestinatairUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DestinatairUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DestinatairUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    destinatairService = TestBed.inject(DestinatairService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const destinatair: IDestinatair = { id: 456 };

      activatedRoute.data = of({ destinatair });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(destinatair));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Destinatair>>();
      const destinatair = { id: 123 };
      jest.spyOn(destinatairService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ destinatair });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: destinatair }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(destinatairService.update).toHaveBeenCalledWith(destinatair);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Destinatair>>();
      const destinatair = new Destinatair();
      jest.spyOn(destinatairService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ destinatair });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: destinatair }));
      saveSubject.complete();

      // THEN
      expect(destinatairService.create).toHaveBeenCalledWith(destinatair);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Destinatair>>();
      const destinatair = { id: 123 };
      jest.spyOn(destinatairService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ destinatair });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(destinatairService.update).toHaveBeenCalledWith(destinatair);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
