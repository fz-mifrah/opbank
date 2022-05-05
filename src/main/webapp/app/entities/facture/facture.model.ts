export interface IFacture {
  id?: number;
  nom?: string;
}

export class Facture implements IFacture {
  constructor(public id?: number, public nom?: string) {}
}

export function getFactureIdentifier(facture: IFacture): number | undefined {
  return facture.id;
}
