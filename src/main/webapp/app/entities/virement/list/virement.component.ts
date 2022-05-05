import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVirement } from '../virement.model';
import { VirementService } from '../service/virement.service';
import { VirementDeleteDialogComponent } from '../delete/virement-delete-dialog.component';

@Component({
  selector: 'jhi-virement',
  templateUrl: './virement.component.html',
})
export class VirementComponent implements OnInit {
  virements?: IVirement[];
  isLoading = false;

  constructor(protected virementService: VirementService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.virementService.query().subscribe({
      next: (res: HttpResponse<IVirement[]>) => {
        this.isLoading = false;
        this.virements = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IVirement): number {
    return item.id!;
  }

  delete(virement: IVirement): void {
    const modalRef = this.modalService.open(VirementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.virement = virement;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
