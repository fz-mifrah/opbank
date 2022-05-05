import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DestinatairService } from '../service/destinatair.service';
import { IDestinatair, Destinatair } from '../destinatair.model';
import { IVirement } from 'app/entities/virement/virement.model';
import { VirementService } from 'app/entities/virement/service/virement.service';

import { DestinatairUpdateComponent } from './destinatair-update.component';

describe('Destinatair Management Update Component', () => {
  let comp: DestinatairUpdateComponent;
  let fixture: ComponentFixture<DestinatairUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let destinatairService: DestinatairService;
  let virementService: VirementService;

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
    virementService = TestBed.inject(VirementService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Virement query and add missing value', () => {
      const destinatair: IDestinatair = { id: 456 };
      const rib: IVirement = { id: 48337 };
      destinatair.rib = rib;

      const virementCollection: IVirement[] = [{ id: 5755 }];
      jest.spyOn(virementService, 'query').mockReturnValue(of(new HttpResponse({ body: virementCollection })));
      const additionalVirements = [rib];
      const expectedCollection: IVirement[] = [...additionalVirements, ...virementCollection];
      jest.spyOn(virementService, 'addVirementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ destinatair });
      comp.ngOnInit();

      expect(virementService.query).toHaveBeenCalled();
      expect(virementService.addVirementToCollectionIfMissing).toHaveBeenCalledWith(virementCollection, ...additionalVirements);
      expect(comp.virementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const destinatair: IDestinatair = { id: 456 };
      const rib: IVirement = { id: 1835 };
      destinatair.rib = rib;

      activatedRoute.data = of({ destinatair });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(destinatair));
      expect(comp.virementsSharedCollection).toContain(rib);
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

  describe('Tracking relationships identifiers', () => {
    describe('trackVirementById', () => {
      it('Should return tracked Virement primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackVirementById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
