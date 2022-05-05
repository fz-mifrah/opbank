import { IOperation } from 'app/entities/operation/operation.model';

export interface IVirement {
  id?: number;
  description?: string | null;
  operation?: IOperation | null;
}

export class Virement implements IVirement {
  constructor(public id?: number, public description?: string | null, public operation?: IOperation | null) {}
}

export function getVirementIdentifier(virement: IVirement): number | undefined {
  return virement.id;
}
