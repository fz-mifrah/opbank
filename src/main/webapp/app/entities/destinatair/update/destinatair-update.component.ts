import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IDestinatair, Destinatair } from '../destinatair.model';
import { DestinatairService } from '../service/destinatair.service';
import { IVirement } from 'app/entities/virement/virement.model';
import { VirementService } from 'app/entities/virement/service/virement.service';

@Component({
  selector: 'jhi-destinatair-update',
  templateUrl: './destinatair-update.component.html',
})
export class DestinatairUpdateComponent implements OnInit {
  isSaving = false;

  virementsSharedCollection: IVirement[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    rib: [null, [Validators.required]],
    rib: [],
  });

  constructor(
    protected destinatairService: DestinatairService,
    protected virementService: VirementService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ destinatair }) => {
      this.updateForm(destinatair);

      this.loadRelationshipsOptions();
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

  trackVirementById(_index: number, item: IVirement): number {
    return item.id!;
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
      rib: destinatair.rib,
    });

    this.virementsSharedCollection = this.virementService.addVirementToCollectionIfMissing(this.virementsSharedCollection, destinatair.rib);
  }

  protected loadRelationshipsOptions(): void {
    this.virementService
      .query()
      .pipe(map((res: HttpResponse<IVirement[]>) => res.body ?? []))
      .pipe(
        map((virements: IVirement[]) => this.virementService.addVirementToCollectionIfMissing(virements, this.editForm.get('rib')!.value))
      )
      .subscribe((virements: IVirement[]) => (this.virementsSharedCollection = virements));
  }

  protected createFromForm(): IDestinatair {
    return {
      ...new Destinatair(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      rib: this.editForm.get(['rib'])!.value,
      rib: this.editForm.get(['rib'])!.value,
    };
  }
}
