export interface IVirement {
  id?: number;
  description?: string | null;
}

export class Virement implements IVirement {
  constructor(public id?: number, public description?: string | null) {}
}

export function getVirementIdentifier(virement: IVirement): number | undefined {
  return virement.id;
}
