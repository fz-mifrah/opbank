export interface ITransfer {
  id?: number;
  cinDestinataireII?: string;
  nomPrenomDestinataireII?: string;
  telDestinataireII?: number | null;
}

export class Transfer implements ITransfer {
  constructor(
    public id?: number,
    public cinDestinataireII?: string,
    public nomPrenomDestinataireII?: string,
    public telDestinataireII?: number | null
  ) {}
}

export function getTransferIdentifier(transfer: ITransfer): number | undefined {
  return transfer.id;
}
