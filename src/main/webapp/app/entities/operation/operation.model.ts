import dayjs from 'dayjs/esm';
import { IVirement } from 'app/entities/virement/virement.model';
import { ITransfer } from 'app/entities/transfer/transfer.model';
import { ICompte } from 'app/entities/compte/compte.model';

export interface IOperation {
  id?: number;
  numOperation?: string;
  date?: dayjs.Dayjs | null;
  typeOperatin?: string | null;
  etatOperation?: string | null;
  montant?: number;
  typeOperation?: IVirement | null;
  typeOperation?: ITransfer | null;
  typeOperation?: ICompte | null;
}

export class Operation implements IOperation {
  constructor(
    public id?: number,
    public numOperation?: string,
    public date?: dayjs.Dayjs | null,
    public typeOperatin?: string | null,
    public etatOperation?: string | null,
    public montant?: number,
    public typeOperation?: IVirement | null,
    public typeOperation?: ITransfer | null,
    public typeOperation?: ICompte | null
  ) {}
}

export function getOperationIdentifier(operation: IOperation): number | undefined {
  return operation.id;
}
