import { Employee } from './employee.model';
import { Product } from './product.model';

export interface Sell {
  employee: Employee;
  product: Product;
  dateTime: Date;
  quantity: number;
}

export interface SellBodyRequest {
  cpf: string;
  barcode: string;
  dateTime: Date;
  quantity: number;
}
