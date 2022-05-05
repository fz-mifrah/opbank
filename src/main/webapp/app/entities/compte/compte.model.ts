import dayjs from 'dayjs/esm';
import { ICarteBancaire } from 'app/entities/carte-bancaire/carte-bancaire.model';
import { IOperation } from 'app/entities/operation/operation.model';
import { IClient } from 'app/entities/client/client.model';
import { IBanque } from 'app/entities/banque/banque.model';

export interface ICompte {
  id?: number;
  rib?: number;
  dateOuvertur?: dayjs.Dayjs | null;
  code?: number;
  nom?: ICarteBancaire | null;
  ribs?: IOperation[] | null;
  client?: IClient | null;
  banque?: IBanque | null;
}

export class Compte implements ICompte {
  constructor(
    public id?: number,
    public rib?: number,
    public dateOuvertur?: dayjs.Dayjs | null,
    public code?: number,
    public nom?: ICarteBancaire | null,
    public ribs?: IOperation[] | null,
    public client?: IClient | null,
    public banque?: IBanque | null
  ) {}
}

export function getCompteIdentifier(compte: ICompte): number | undefined {
  return compte.id;
}
