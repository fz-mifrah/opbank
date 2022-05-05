export interface IBeneficiaire {
  id?: number;
  nomPrenom?: string;
  numCompte?: number;
}

export class Beneficiaire implements IBeneficiaire {
  constructor(public id?: number, public nomPrenom?: string, public numCompte?: number) {}
}

export function getBeneficiaireIdentifier(beneficiaire: IBeneficiaire): number | undefined {
  return beneficiaire.id;
}
