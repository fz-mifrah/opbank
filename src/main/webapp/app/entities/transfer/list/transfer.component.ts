import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITransfer } from '../transfer.model';
import { TransferService } from '../service/transfer.service';
import { TransferDeleteDialogComponent } from '../delete/transfer-delete-dialog.component';

@Component({
  selector: 'jhi-transfer',
  templateUrl: './transfer.component.html',
})
export class TransferComponent implements OnInit {
  transfers?: ITransfer[];
  isLoading = false;

  constructor(protected transferService: TransferService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.transferService.query().subscribe({
      next: (res: HttpResponse<ITransfer[]>) => {
        this.isLoading = false;
        this.transfers = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: ITransfer): number {
    return item.id!;
  }

  delete(transfer: ITransfer): void {
    const modalRef = this.modalService.open(TransferDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.transfer = transfer;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
