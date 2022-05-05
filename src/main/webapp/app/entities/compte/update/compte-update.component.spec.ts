import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CompteService } from '../service/compte.service';
import { ICompte, Compte } from '../compte.model';
import { ICarteBancaire } from 'app/entities/carte-bancaire/carte-bancaire.model';
import { CarteBancaireService } from 'app/entities/carte-bancaire/service/carte-bancaire.service';

import { CompteUpdateComponent } from './compte-update.component';

describe('Compte Management Update Component', () => {
  let comp: CompteUpdateComponent;
  let fixture: ComponentFixture<CompteUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let compteService: CompteService;
  let carteBancaireService: CarteBancaireService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CompteUpdateComponent],
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
      .overrideTemplate(CompteUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CompteUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    compteService = TestBed.inject(CompteService);
    carteBancaireService = TestBed.inject(CarteBancaireService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call nom query and add missing value', () => {
      const compte: ICompte = { id: 456 };
      const nom: ICarteBancaire = { id: 12922 };
      compte.nom = nom;

      const nomCollection: ICarteBancaire[] = [{ id: 97704 }];
      jest.spyOn(carteBancaireService, 'query').mockReturnValue(of(new HttpResponse({ body: nomCollection })));
      const expectedCollection: ICarteBancaire[] = [nom, ...nomCollection];
      jest.spyOn(carteBancaireService, 'addCarteBancaireToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ compte });
      comp.ngOnInit();

      expect(carteBancaireService.query).toHaveBeenCalled();
      expect(carteBancaireService.addCarteBancaireToCollectionIfMissing).toHaveBeenCalledWith(nomCollection, nom);
      expect(comp.nomsCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const compte: ICompte = { id: 456 };
      const nom: ICarteBancaire = { id: 25178 };
      compte.nom = nom;

      activatedRoute.data = of({ compte });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(compte));
      expect(comp.nomsCollection).toContain(nom);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Compte>>();
      const compte = { id: 123 };
      jest.spyOn(compteService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ compte });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: compte }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(compteService.update).toHaveBeenCalledWith(compte);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Compte>>();
      const compte = new Compte();
      jest.spyOn(compteService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ compte });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: compte }));
      saveSubject.complete();

      // THEN
      expect(compteService.create).toHaveBeenCalledWith(compte);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Compte>>();
      const compte = { id: 123 };
      jest.spyOn(compteService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ compte });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(compteService.update).toHaveBeenCalledWith(compte);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackCarteBancaireById', () => {
      it('Should return tracked CarteBancaire primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackCarteBancaireById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
