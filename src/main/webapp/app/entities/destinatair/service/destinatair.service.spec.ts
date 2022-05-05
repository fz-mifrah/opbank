import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDestinatair, Destinatair } from '../destinatair.model';

import { DestinatairService } from './destinatair.service';

describe('Destinatair Service', () => {
  let service: DestinatairService;
  let httpMock: HttpTestingController;
  let elemDefault: IDestinatair;
  let expectedResult: IDestinatair | IDestinatair[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DestinatairService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      nom: 'AAAAAAA',
      prenom: 'AAAAAAA',
      rib: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Destinatair', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Destinatair()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Destinatair', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nom: 'BBBBBB',
          prenom: 'BBBBBB',
          rib: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Destinatair', () => {
      const patchObject = Object.assign(
        {
          rib: 1,
        },
        new Destinatair()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Destinatair', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nom: 'BBBBBB',
          prenom: 'BBBBBB',
          rib: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Destinatair', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDestinatairToCollectionIfMissing', () => {
      it('should add a Destinatair to an empty array', () => {
        const destinatair: IDestinatair = { id: 123 };
        expectedResult = service.addDestinatairToCollectionIfMissing([], destinatair);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(destinatair);
      });

      it('should not add a Destinatair to an array that contains it', () => {
        const destinatair: IDestinatair = { id: 123 };
        const destinatairCollection: IDestinatair[] = [
          {
            ...destinatair,
          },
          { id: 456 },
        ];
        expectedResult = service.addDestinatairToCollectionIfMissing(destinatairCollection, destinatair);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Destinatair to an array that doesn't contain it", () => {
        const destinatair: IDestinatair = { id: 123 };
        const destinatairCollection: IDestinatair[] = [{ id: 456 }];
        expectedResult = service.addDestinatairToCollectionIfMissing(destinatairCollection, destinatair);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(destinatair);
      });

      it('should add only unique Destinatair to an array', () => {
        const destinatairArray: IDestinatair[] = [{ id: 123 }, { id: 456 }, { id: 53679 }];
        const destinatairCollection: IDestinatair[] = [{ id: 123 }];
        expectedResult = service.addDestinatairToCollectionIfMissing(destinatairCollection, ...destinatairArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const destinatair: IDestinatair = { id: 123 };
        const destinatair2: IDestinatair = { id: 456 };
        expectedResult = service.addDestinatairToCollectionIfMissing([], destinatair, destinatair2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(destinatair);
        expect(expectedResult).toContain(destinatair2);
      });

      it('should accept null and undefined values', () => {
        const destinatair: IDestinatair = { id: 123 };
        expectedResult = service.addDestinatairToCollectionIfMissing([], null, destinatair, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(destinatair);
      });

      it('should return initial array if no Destinatair is added', () => {
        const destinatairCollection: IDestinatair[] = [{ id: 123 }];
        expectedResult = service.addDestinatairToCollectionIfMissing(destinatairCollection, undefined, null);
        expect(expectedResult).toEqual(destinatairCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
