import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DestinatairComponent } from '../list/destinatair.component';
import { DestinatairDetailComponent } from '../detail/destinatair-detail.component';
import { DestinatairUpdateComponent } from '../update/destinatair-update.component';
import { DestinatairRoutingResolveService } from './destinatair-routing-resolve.service';

const destinatairRoute: Routes = [
  {
    path: '',
    component: DestinatairComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DestinatairDetailComponent,
    resolve: {
      destinatair: DestinatairRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DestinatairUpdateComponent,
    resolve: {
      destinatair: DestinatairRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DestinatairUpdateComponent,
    resolve: {
      destinatair: DestinatairRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(destinatairRoute)],
  exports: [RouterModule],
})
export class DestinatairRoutingModule {}
