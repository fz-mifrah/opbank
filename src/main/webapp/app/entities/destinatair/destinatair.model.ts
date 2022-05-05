import { IVirement } from 'app/entities/virement/virement.model';

export interface IDestinatair {
  id?: number;
  nom?: string;
  prenom?: string;
  rib?: number;
  rib?: IVirement | null;
}

export class Destinatair implements IDestinatair {
  constructor(public id?: number, public nom?: string, public prenom?: string, public rib?: number, public rib?: IVirement | null) {}
}

export function getDestinatairIdentifier(destinatair: IDestinatair): number | undefined {
  return destinatair.id;
}
