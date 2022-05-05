export interface ICarteBancaire {
  id?: number;
  numCompte?: string;
}

export class CarteBancaire implements ICarteBancaire {
  constructor(public id?: number, public numCompte?: string) {}
}

export function getCarteBancaireIdentifier(carteBancaire: ICarteBancaire): number | undefined {
  return carteBancaire.id;
}
