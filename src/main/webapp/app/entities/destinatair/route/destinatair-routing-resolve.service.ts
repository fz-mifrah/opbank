import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDestinatair, Destinatair } from '../destinatair.model';
import { DestinatairService } from '../service/destinatair.service';

@Injectable({ providedIn: 'root' })
export class DestinatairRoutingResolveService implements Resolve<IDestinatair> {
  constructor(protected service: DestinatairService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDestinatair> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((destinatair: HttpResponse<Destinatair>) => {
          if (destinatair.body) {
            return of(destinatair.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Destinatair());
  }
}
