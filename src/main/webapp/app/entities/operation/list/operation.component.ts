import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOperation } from '../operation.model';
import { OperationService } from '../service/operation.service';
import { OperationDeleteDialogComponent } from '../delete/operation-delete-dialog.component';

@Component({
  selector: 'jhi-operation',
  templateUrl: './operation.component.html',
})
export class OperationComponent implements OnInit {
  operations?: IOperation[];
  isLoading = false;

  constructor(protected operationService: OperationService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.operationService.query().subscribe({
      next: (res: HttpResponse<IOperation[]>) => {
        this.isLoading = false;
        this.operations = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IOperation): number {
    return item.id!;
  }

  delete(operation: IOperation): void {
    const modalRef = this.modalService.open(OperationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.operation = operation;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
