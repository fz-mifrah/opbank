import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDestinatair, Destinatair } from '../destinatair.model';
import { DestinatairService } from '../service/destinatair.service';

import { DestinatairRoutingResolveService } from './destinatair-routing-resolve.service';

describe('Destinatair routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DestinatairRoutingResolveService;
  let service: DestinatairService;
  let resultDestinatair: IDestinatair | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(DestinatairRoutingResolveService);
    service = TestBed.inject(DestinatairService);
    resultDestinatair = undefined;
  });

  describe('resolve', () => {
    it('should return IDestinatair returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDestinatair = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDestinatair).toEqual({ id: 123 });
    });

    it('should return new IDestinatair if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDestinatair = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDestinatair).toEqual(new Destinatair());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as Destinatair })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDestinatair = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDestinatair).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
