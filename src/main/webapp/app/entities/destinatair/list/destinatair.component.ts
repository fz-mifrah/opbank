import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDestinatair } from '../destinatair.model';
import { DestinatairService } from '../service/destinatair.service';
import { DestinatairDeleteDialogComponent } from '../delete/destinatair-delete-dialog.component';

@Component({
  selector: 'jhi-destinatair',
  templateUrl: './destinatair.component.html',
})
export class DestinatairComponent implements OnInit {
  destinatairs?: IDestinatair[];
  isLoading = false;

  constructor(protected destinatairService: DestinatairService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.destinatairService.query().subscribe({
      next: (res: HttpResponse<IDestinatair[]>) => {
        this.isLoading = false;
        this.destinatairs = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IDestinatair): number {
    return item.id!;
  }

  delete(destinatair: IDestinatair): void {
    const modalRef = this.modalService.open(DestinatairDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.destinatair = destinatair;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
