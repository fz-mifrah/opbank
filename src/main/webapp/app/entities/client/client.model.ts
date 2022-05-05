import dayjs from 'dayjs/esm';

export interface IClient {
  id?: number;
  cin?: string;
  nom?: string;
  prenom?: string;
  adress?: string;
  postalCode?: number;
  dateNaissence?: dayjs.Dayjs;
  email?: string;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public cin?: string,
    public nom?: string,
    public prenom?: string,
    public adress?: string,
    public postalCode?: number,
    public dateNaissence?: dayjs.Dayjs,
    public email?: string
  ) {}
}

export function getClientIdentifier(client: IClient): number | undefined {
  return client.id;
}
