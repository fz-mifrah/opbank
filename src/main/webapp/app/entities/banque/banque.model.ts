export interface IBanque {
  id?: number;
  nom?: string;
  adresse?: string | null;
  email?: string;
}

export class Banque implements IBanque {
  constructor(public id?: number, public nom?: string, public adresse?: string | null, public email?: string) {}
}

export function getBanqueIdentifier(banque: IBanque): number | undefined {
  return banque.id;
}
