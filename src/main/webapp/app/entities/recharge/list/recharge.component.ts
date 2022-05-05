import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRecharge } from '../recharge.model';
import { RechargeService } from '../service/recharge.service';
import { RechargeDeleteDialogComponent } from '../delete/recharge-delete-dialog.component';

@Component({
  selector: 'jhi-recharge',
  templateUrl: './recharge.component.html',
})
export class RechargeComponent implements OnInit {
  recharges?: IRecharge[];
  isLoading = false;

  constructor(protected rechargeService: RechargeService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.rechargeService.query().subscribe({
      next: (res: HttpResponse<IRecharge[]>) => {
        this.isLoading = false;
        this.recharges = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IRecharge): number {
    return item.id!;
  }

  delete(recharge: IRecharge): void {
    const modalRef = this.modalService.open(RechargeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.recharge = recharge;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
