import { IDestinatair } from 'app/entities/destinatair/destinatair.model';
import { IBeneficiaire } from 'app/entities/beneficiaire/beneficiaire.model';
import { IOperation } from 'app/entities/operation/operation.model';

export interface IVirement {
  id?: number;
  description?: string | null;
  destinataires?: IDestinatair[] | null;
  beneficiaires?: IBeneficiaire[] | null;
  operation?: IOperation | null;
}

export class Virement implements IVirement {
  constructor(
    public id?: number,
    public description?: string | null,
    public destinataires?: IDestinatair[] | null,
    public beneficiaires?: IBeneficiaire[] | null,
    public operation?: IOperation | null
  ) {}
}

export function getVirementIdentifier(virement: IVirement): number | undefined {
  return virement.id;
}
