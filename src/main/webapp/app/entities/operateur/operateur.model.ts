export interface IOperateur {
  id?: number;
  nom?: string;
}

export class Operateur implements IOperateur {
  constructor(public id?: number, public nom?: string) {}
}

export function getOperateurIdentifier(operateur: IOperateur): number | undefined {
  return operateur.id;
}
