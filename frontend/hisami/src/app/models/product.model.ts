import { Raw, RawQuantity } from './raw.model';

export interface Registering {
  product: Product;
  raws: { raw: Raw; quantity: number }[];
}

export interface Product {
  name: string;
  description: string;
  price: number;
  cust: number;
  percentCustPrice: number;
  barcode: string;
  image: string | null;
  raws: RawQuantity[];
  stockQuantity: number;
}
