import dayjs from 'dayjs/esm';

export interface ICompte {
  id?: number;
  rib?: number;
  dateOuvertur?: dayjs.Dayjs | null;
  code?: number;
}

export class Compte implements ICompte {
  constructor(public id?: number, public rib?: number, public dateOuvertur?: dayjs.Dayjs | null, public code?: number) {}
}

export function getCompteIdentifier(compte: ICompte): number | undefined {
  return compte.id;
}
