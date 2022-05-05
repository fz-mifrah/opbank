export interface IRecharge {
  id?: number;
  numTel?: number;
}

export class Recharge implements IRecharge {
  constructor(public id?: number, public numTel?: number) {}
}

export function getRechargeIdentifier(recharge: IRecharge): number | undefined {
  return recharge.id;
}
