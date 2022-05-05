import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { ICompte, Compte } from '../compte.model';
import { CompteService } from '../service/compte.service';
import { ICarteBancaire } from 'app/entities/carte-bancaire/carte-bancaire.model';
import { CarteBancaireService } from 'app/entities/carte-bancaire/service/carte-bancaire.service';

@Component({
  selector: 'jhi-compte-update',
  templateUrl: './compte-update.component.html',
})
export class CompteUpdateComponent implements OnInit {
  isSaving = false;

  nomsCollection: ICarteBancaire[] = [];

  editForm = this.fb.group({
    id: [],
    rib: [null, [Validators.required]],
    dateOuvertur: [],
    code: [null, [Validators.required]],
    nom: [],
  });

  constructor(
    protected compteService: CompteService,
    protected carteBancaireService: CarteBancaireService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compte }) => {
      if (compte.id === undefined) {
        const today = dayjs().startOf('day');
        compte.dateOuvertur = today;
      }

      this.updateForm(compte);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const compte = this.createFromForm();
    if (compte.id !== undefined) {
      this.subscribeToSaveResponse(this.compteService.update(compte));
    } else {
      this.subscribeToSaveResponse(this.compteService.create(compte));
    }
  }

  trackCarteBancaireById(_index: number, item: ICarteBancaire): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompte>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(compte: ICompte): void {
    this.editForm.patchValue({
      id: compte.id,
      rib: compte.rib,
      dateOuvertur: compte.dateOuvertur ? compte.dateOuvertur.format(DATE_TIME_FORMAT) : null,
      code: compte.code,
      nom: compte.nom,
    });

    this.nomsCollection = this.carteBancaireService.addCarteBancaireToCollectionIfMissing(this.nomsCollection, compte.nom);
  }

  protected loadRelationshipsOptions(): void {
    this.carteBancaireService
      .query({ filter: 'compte-is-null' })
      .pipe(map((res: HttpResponse<ICarteBancaire[]>) => res.body ?? []))
      .pipe(
        map((carteBancaires: ICarteBancaire[]) =>
          this.carteBancaireService.addCarteBancaireToCollectionIfMissing(carteBancaires, this.editForm.get('nom')!.value)
        )
      )
      .subscribe((carteBancaires: ICarteBancaire[]) => (this.nomsCollection = carteBancaires));
  }

  protected createFromForm(): ICompte {
    return {
      ...new Compte(),
      id: this.editForm.get(['id'])!.value,
      rib: this.editForm.get(['rib'])!.value,
      dateOuvertur: this.editForm.get(['dateOuvertur'])!.value
        ? dayjs(this.editForm.get(['dateOuvertur'])!.value, DATE_TIME_FORMAT)
        : undefined,
      code: this.editForm.get(['code'])!.value,
      nom: this.editForm.get(['nom'])!.value,
    };
  }
}
