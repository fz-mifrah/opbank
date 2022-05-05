import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IOperation, Operation } from '../operation.model';
import { OperationService } from '../service/operation.service';
import { IVirement } from 'app/entities/virement/virement.model';
import { VirementService } from 'app/entities/virement/service/virement.service';
import { ICompte } from 'app/entities/compte/compte.model';
import { CompteService } from 'app/entities/compte/service/compte.service';

@Component({
  selector: 'jhi-operation-update',
  templateUrl: './operation-update.component.html',
})
export class OperationUpdateComponent implements OnInit {
  isSaving = false;

  virementsCollection: IVirement[] = [];
  comptesSharedCollection: ICompte[] = [];

  editForm = this.fb.group({
    id: [],
    numOperation: [null, [Validators.required]],
    date: [],
    typeOperatin: [null, [Validators.required]],
    etatOperation: [],
    montant: [null, [Validators.required]],
    virement: [],
    typeOperation: [],
  });

  constructor(
    protected operationService: OperationService,
    protected virementService: VirementService,
    protected compteService: CompteService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operation }) => {
      if (operation.id === undefined) {
        const today = dayjs().startOf('day');
        operation.date = today;
      }

      this.updateForm(operation);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operation = this.createFromForm();
    if (operation.id !== undefined) {
      this.subscribeToSaveResponse(this.operationService.update(operation));
    } else {
      this.subscribeToSaveResponse(this.operationService.create(operation));
    }
  }

  trackVirementById(_index: number, item: IVirement): number {
    return item.id!;
  }

  trackCompteById(_index: number, item: ICompte): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperation>>): void {
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

  protected updateForm(operation: IOperation): void {
    this.editForm.patchValue({
      id: operation.id,
      numOperation: operation.numOperation,
      date: operation.date ? operation.date.format(DATE_TIME_FORMAT) : null,
      typeOperatin: operation.typeOperatin,
      etatOperation: operation.etatOperation,
      montant: operation.montant,
      virement: operation.virement,
      typeOperation: operation.typeOperation,
    });

    this.virementsCollection = this.virementService.addVirementToCollectionIfMissing(this.virementsCollection, operation.virement);
    this.comptesSharedCollection = this.compteService.addCompteToCollectionIfMissing(this.comptesSharedCollection, operation.typeOperation);
  }

  protected loadRelationshipsOptions(): void {
    this.virementService
      .query({ filter: 'operation-is-null' })
      .pipe(map((res: HttpResponse<IVirement[]>) => res.body ?? []))
      .pipe(
        map((virements: IVirement[]) =>
          this.virementService.addVirementToCollectionIfMissing(virements, this.editForm.get('virement')!.value)
        )
      )
      .subscribe((virements: IVirement[]) => (this.virementsCollection = virements));

    this.compteService
      .query()
      .pipe(map((res: HttpResponse<ICompte[]>) => res.body ?? []))
      .pipe(
        map((comptes: ICompte[]) => this.compteService.addCompteToCollectionIfMissing(comptes, this.editForm.get('typeOperation')!.value))
      )
      .subscribe((comptes: ICompte[]) => (this.comptesSharedCollection = comptes));
  }

  protected createFromForm(): IOperation {
    return {
      ...new Operation(),
      id: this.editForm.get(['id'])!.value,
      numOperation: this.editForm.get(['numOperation'])!.value,
      date: this.editForm.get(['date'])!.value ? dayjs(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      typeOperatin: this.editForm.get(['typeOperatin'])!.value,
      etatOperation: this.editForm.get(['etatOperation'])!.value,
      montant: this.editForm.get(['montant'])!.value,
      virement: this.editForm.get(['virement'])!.value,
      typeOperation: this.editForm.get(['typeOperation'])!.value,
    };
  }
}
