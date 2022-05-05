import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DestinatairComponent } from './list/destinatair.component';
import { DestinatairDetailComponent } from './detail/destinatair-detail.component';
import { DestinatairUpdateComponent } from './update/destinatair-update.component';
import { DestinatairDeleteDialogComponent } from './delete/destinatair-delete-dialog.component';
import { DestinatairRoutingModule } from './route/destinatair-routing.module';

@NgModule({
  imports: [SharedModule, DestinatairRoutingModule],
  declarations: [DestinatairComponent, DestinatairDetailComponent, DestinatairUpdateComponent, DestinatairDeleteDialogComponent],
  entryComponents: [DestinatairDeleteDialogComponent],
})
export class DestinatairModule {}
