export interface Raw {
  name: string;
  cust: number;
}

export interface RawQuantity {
  raw: Raw;
  quantity: number;
}
