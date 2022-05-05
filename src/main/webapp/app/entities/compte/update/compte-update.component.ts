import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { ICompte, Compte } from '../compte.model';
import { CompteService } from '../service/compte.service';

@Component({
  selector: 'jhi-compte-update',
  templateUrl: './compte-update.component.html',
})
export class CompteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    rib: [null, [Validators.required]],
    dateOuvertur: [],
    code: [null, [Validators.required]],
  });

  constructor(protected compteService: CompteService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compte }) => {
      if (compte.id === undefined) {
        const today = dayjs().startOf('day');
        compte.dateOuvertur = today;
      }

      this.updateForm(compte);
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
    });
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
    };
  }
}
