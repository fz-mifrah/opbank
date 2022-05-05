import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IRecharge, Recharge } from '../recharge.model';
import { RechargeService } from '../service/recharge.service';

@Component({
  selector: 'jhi-recharge-update',
  templateUrl: './recharge-update.component.html',
})
export class RechargeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    numTel: [null, [Validators.required]],
  });

  constructor(protected rechargeService: RechargeService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ recharge }) => {
      this.updateForm(recharge);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const recharge = this.createFromForm();
    if (recharge.id !== undefined) {
      this.subscribeToSaveResponse(this.rechargeService.update(recharge));
    } else {
      this.subscribeToSaveResponse(this.rechargeService.create(recharge));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecharge>>): void {
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

  protected updateForm(recharge: IRecharge): void {
    this.editForm.patchValue({
      id: recharge.id,
      numTel: recharge.numTel,
    });
  }

  protected createFromForm(): IRecharge {
    return {
      ...new Recharge(),
      id: this.editForm.get(['id'])!.value,
      numTel: this.editForm.get(['numTel'])!.value,
    };
  }
}
