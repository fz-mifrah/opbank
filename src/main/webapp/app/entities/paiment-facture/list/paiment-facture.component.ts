import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPaimentFacture } from '../paiment-facture.model';
import { PaimentFactureService } from '../service/paiment-facture.service';
import { PaimentFactureDeleteDialogComponent } from '../delete/paiment-facture-delete-dialog.component';

@Component({
  selector: 'jhi-paiment-facture',
  templateUrl: './paiment-facture.component.html',
})
export class PaimentFactureComponent implements OnInit {
  paimentFactures?: IPaimentFacture[];
  isLoading = false;

  constructor(protected paimentFactureService: PaimentFactureService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.paimentFactureService.query().subscribe({
      next: (res: HttpResponse<IPaimentFacture[]>) => {
        this.isLoading = false;
        this.paimentFactures = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IPaimentFacture): number {
    return item.id!;
  }

  delete(paimentFacture: IPaimentFacture): void {
    const modalRef = this.modalService.open(PaimentFactureDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paimentFacture = paimentFacture;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
