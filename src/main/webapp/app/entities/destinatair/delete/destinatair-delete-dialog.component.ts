import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDestinatair } from '../destinatair.model';
import { DestinatairService } from '../service/destinatair.service';

@Component({
  templateUrl: './destinatair-delete-dialog.component.html',
})
export class DestinatairDeleteDialogComponent {
  destinatair?: IDestinatair;

  constructor(protected destinatairService: DestinatairService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.destinatairService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
