export interface IPaimentFacture {
  id?: number;
  referance?: number;
}

export class PaimentFacture implements IPaimentFacture {
  constructor(public id?: number, public referance?: number) {}
}

export function getPaimentFactureIdentifier(paimentFacture: IPaimentFacture): number | undefined {
  return paimentFacture.id;
}
