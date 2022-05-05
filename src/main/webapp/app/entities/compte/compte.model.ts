import dayjs from 'dayjs/esm';
import { ICarteBancaire } from 'app/entities/carte-bancaire/carte-bancaire.model';
import { IClient } from 'app/entities/client/client.model';

export interface ICompte {
  id?: number;
  rib?: number;
  dateOuvertur?: dayjs.Dayjs | null;
  code?: number;
  nom?: ICarteBancaire | null;
  client?: IClient | null;
}

export class Compte implements ICompte {
  constructor(
    public id?: number,
    public rib?: number,
    public dateOuvertur?: dayjs.Dayjs | null,
    public code?: number,
    public nom?: ICarteBancaire | null,
    public client?: IClient | null
  ) {}
}

export function getCompteIdentifier(compte: ICompte): number | undefined {
  return compte.id;
}
