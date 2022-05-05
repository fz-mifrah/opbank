import { ICompte } from 'app/entities/compte/compte.model';

export interface IBanque {
  id?: number;
  nom?: string;
  adresse?: string | null;
  email?: string;
  noms?: ICompte[] | null;
}

export class Banque implements IBanque {
  constructor(
    public id?: number,
    public nom?: string,
    public adresse?: string | null,
    public email?: string,
    public noms?: ICompte[] | null
  ) {}
}

export function getBanqueIdentifier(banque: IBanque): number | undefined {
  return banque.id;
}
