import dayjs from 'dayjs/esm';
import { ICompte } from 'app/entities/compte/compte.model';

export interface IOperation {
  id?: number;
  numOperation?: string;
  date?: dayjs.Dayjs | null;
  typeOperatin?: string;
  etatOperation?: string | null;
  montant?: number;
  typeOperation?: ICompte | null;
}

export class Operation implements IOperation {
  constructor(
    public id?: number,
    public numOperation?: string,
    public date?: dayjs.Dayjs | null,
    public typeOperatin?: string,
    public etatOperation?: string | null,
    public montant?: number,
    public typeOperation?: ICompte | null
  ) {}
}

export function getOperationIdentifier(operation: IOperation): number | undefined {
  return operation.id;
}
