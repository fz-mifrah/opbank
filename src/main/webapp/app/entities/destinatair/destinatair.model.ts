export interface IDestinatair {
  id?: number;
  nom?: string;
  prenom?: string;
  rib?: number;
}

export class Destinatair implements IDestinatair {
  constructor(public id?: number, public nom?: string, public prenom?: string, public rib?: number) {}
}

export function getDestinatairIdentifier(destinatair: IDestinatair): number | undefined {
  return destinatair.id;
}
