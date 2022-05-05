import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IDestinatair, Destinatair } from '../destinatair.model';
import { DestinatairService } from '../service/destinatair.service';

@Component({
  selector: 'jhi-destinatair-update',
  templateUrl: './destinatair-update.component.html',
})
export class DestinatairUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    rib: [null, [Validators.required]],
  });

  constructor(protected destinatairService: DestinatairService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ destinatair }) => {
      this.updateForm(destinatair);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const destinatair = this.createFromForm();
    if (destinatair.id !== undefined) {
      this.subscribeToSaveResponse(this.destinatairService.update(destinatair));
    } else {
      this.subscribeToSaveResponse(this.destinatairService.create(destinatair));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDestinatair>>): void {
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

  protected updateForm(destinatair: IDestinatair): void {
    this.editForm.patchValue({
      id: destinatair.id,
      nom: destinatair.nom,
      prenom: destinatair.prenom,
      rib: destinatair.rib,
    });
  }

  protected createFromForm(): IDestinatair {
    return {
      ...new Destinatair(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      rib: this.editForm.get(['rib'])!.value,
    };
  }
}
