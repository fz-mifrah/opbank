import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDestinatair, getDestinatairIdentifier } from '../destinatair.model';

export type EntityResponseType = HttpResponse<IDestinatair>;
export type EntityArrayResponseType = HttpResponse<IDestinatair[]>;

@Injectable({ providedIn: 'root' })
export class DestinatairService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/destinatairs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(destinatair: IDestinatair): Observable<EntityResponseType> {
    return this.http.post<IDestinatair>(this.resourceUrl, destinatair, { observe: 'response' });
  }

  update(destinatair: IDestinatair): Observable<EntityResponseType> {
    return this.http.put<IDestinatair>(`${this.resourceUrl}/${getDestinatairIdentifier(destinatair) as number}`, destinatair, {
      observe: 'response',
    });
  }

  partialUpdate(destinatair: IDestinatair): Observable<EntityResponseType> {
    return this.http.patch<IDestinatair>(`${this.resourceUrl}/${getDestinatairIdentifier(destinatair) as number}`, destinatair, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDestinatair>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDestinatair[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDestinatairToCollectionIfMissing(
    destinatairCollection: IDestinatair[],
    ...destinatairsToCheck: (IDestinatair | null | undefined)[]
  ): IDestinatair[] {
    const destinatairs: IDestinatair[] = destinatairsToCheck.filter(isPresent);
    if (destinatairs.length > 0) {
      const destinatairCollectionIdentifiers = destinatairCollection.map(destinatairItem => getDestinatairIdentifier(destinatairItem)!);
      const destinatairsToAdd = destinatairs.filter(destinatairItem => {
        const destinatairIdentifier = getDestinatairIdentifier(destinatairItem);
        if (destinatairIdentifier == null || destinatairCollectionIdentifiers.includes(destinatairIdentifier)) {
          return false;
        }
        destinatairCollectionIdentifiers.push(destinatairIdentifier);
        return true;
      });
      return [...destinatairsToAdd, ...destinatairCollection];
    }
    return destinatairCollection;
  }
}
