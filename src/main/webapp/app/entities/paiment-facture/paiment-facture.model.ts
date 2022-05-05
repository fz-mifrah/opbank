import { IOperation } from 'app/entities/operation/operation.model';

export interface IPaimentFacture {
  id?: number;
  referance?: number;
  operation?: IOperation | null;
}

export class PaimentFacture implements IPaimentFacture {
  constructor(public id?: number, public referance?: number, public operation?: IOperation | null) {}
}

export function getPaimentFactureIdentifier(paimentFacture: IPaimentFacture): number | undefined {
  return paimentFacture.id;
}
