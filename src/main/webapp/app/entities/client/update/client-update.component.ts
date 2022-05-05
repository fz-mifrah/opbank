import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IClient, Client } from '../client.model';
import { ClientService } from '../service/client.service';
import { ICompte } from 'app/entities/compte/compte.model';
import { CompteService } from 'app/entities/compte/service/compte.service';

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html',
})
export class ClientUpdateComponent implements OnInit {
  isSaving = false;

  nomsCollection: ICompte[] = [];

  editForm = this.fb.group({
    id: [],
    cin: [null, [Validators.required]],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    adress: [null, [Validators.required]],
    postalCode: [null, [Validators.required]],
    dateNaissence: [null, [Validators.required]],
    email: [null, [Validators.required]],
    nom: [],
  });

  constructor(
    protected clientService: ClientService,
    protected compteService: CompteService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ client }) => {
      if (client.id === undefined) {
        const today = dayjs().startOf('day');
        client.dateNaissence = today;
      }

      this.updateForm(client);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  trackCompteById(_index: number, item: ICompte): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>): void {
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

  protected updateForm(client: IClient): void {
    this.editForm.patchValue({
      id: client.id,
      cin: client.cin,
      nom: client.nom,
      prenom: client.prenom,
      adress: client.adress,
      postalCode: client.postalCode,
      dateNaissence: client.dateNaissence ? client.dateNaissence.format(DATE_TIME_FORMAT) : null,
      email: client.email,
      nom: client.nom,
    });

    this.nomsCollection = this.compteService.addCompteToCollectionIfMissing(this.nomsCollection, client.nom);
  }

  protected loadRelationshipsOptions(): void {
    this.compteService
      .query({ filter: 'client-is-null' })
      .pipe(map((res: HttpResponse<ICompte[]>) => res.body ?? []))
      .pipe(map((comptes: ICompte[]) => this.compteService.addCompteToCollectionIfMissing(comptes, this.editForm.get('nom')!.value)))
      .subscribe((comptes: ICompte[]) => (this.nomsCollection = comptes));
  }

  protected createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id'])!.value,
      cin: this.editForm.get(['cin'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      adress: this.editForm.get(['adress'])!.value,
      postalCode: this.editForm.get(['postalCode'])!.value,
      dateNaissence: this.editForm.get(['dateNaissence'])!.value
        ? dayjs(this.editForm.get(['dateNaissence'])!.value, DATE_TIME_FORMAT)
        : undefined,
      email: this.editForm.get(['email'])!.value,
      nom: this.editForm.get(['nom'])!.value,
    };
  }
}
