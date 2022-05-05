import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IPaimentFacture, PaimentFacture } from '../paiment-facture.model';
import { PaimentFactureService } from '../service/paiment-facture.service';

@Component({
  selector: 'jhi-paiment-facture-update',
  templateUrl: './paiment-facture-update.component.html',
})
export class PaimentFactureUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    referance: [null, [Validators.required]],
  });

  constructor(
    protected paimentFactureService: PaimentFactureService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paimentFacture }) => {
      this.updateForm(paimentFacture);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paimentFacture = this.createFromForm();
    if (paimentFacture.id !== undefined) {
      this.subscribeToSaveResponse(this.paimentFactureService.update(paimentFacture));
    } else {
      this.subscribeToSaveResponse(this.paimentFactureService.create(paimentFacture));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaimentFacture>>): void {
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

  protected updateForm(paimentFacture: IPaimentFacture): void {
    this.editForm.patchValue({
      id: paimentFacture.id,
      referance: paimentFacture.referance,
    });
  }

  protected createFromForm(): IPaimentFacture {
    return {
      ...new PaimentFacture(),
      id: this.editForm.get(['id'])!.value,
      referance: this.editForm.get(['referance'])!.value,
    };
  }
}
