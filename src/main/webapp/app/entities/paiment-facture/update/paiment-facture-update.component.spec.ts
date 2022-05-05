import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PaimentFactureService } from '../service/paiment-facture.service';
import { IPaimentFacture, PaimentFacture } from '../paiment-facture.model';

import { PaimentFactureUpdateComponent } from './paiment-facture-update.component';

describe('PaimentFacture Management Update Component', () => {
  let comp: PaimentFactureUpdateComponent;
  let fixture: ComponentFixture<PaimentFactureUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let paimentFactureService: PaimentFactureService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PaimentFactureUpdateComponent],
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
      .overrideTemplate(PaimentFactureUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PaimentFactureUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    paimentFactureService = TestBed.inject(PaimentFactureService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const paimentFacture: IPaimentFacture = { id: 456 };

      activatedRoute.data = of({ paimentFacture });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(paimentFacture));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<PaimentFacture>>();
      const paimentFacture = { id: 123 };
      jest.spyOn(paimentFactureService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ paimentFacture });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: paimentFacture }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(paimentFactureService.update).toHaveBeenCalledWith(paimentFacture);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<PaimentFacture>>();
      const paimentFacture = new PaimentFacture();
      jest.spyOn(paimentFactureService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ paimentFacture });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: paimentFacture }));
      saveSubject.complete();

      // THEN
      expect(paimentFactureService.create).toHaveBeenCalledWith(paimentFacture);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<PaimentFacture>>();
      const paimentFacture = { id: 123 };
      jest.spyOn(paimentFactureService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ paimentFacture });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(paimentFactureService.update).toHaveBeenCalledWith(paimentFacture);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
