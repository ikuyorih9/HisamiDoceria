export interface Raw {
  name: string;
  cust: number;
  stockQuantity: number;
}

export interface RawQuantity {
  raw: Raw;
  quantity: number;
}
