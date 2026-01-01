import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

import { Raw, RawQuantity } from '../models/raw.model';
import { Product, Registering } from '../models/product.model';

@Injectable({ providedIn: 'root' })
export class ProductService {
  readonly productUrl = 'http://localhost:8080/api/product';
  constructor(private apiService: ApiService) {}

  public async getProducts(): Promise<Product[]> {
    return await this.apiService.get(this.productUrl);
  }

  public productRegistering(product: Product): Promise<Response> {
    const registering: Registering = {
      product: product,
      raws: product.raws,
    };

    return this.apiService.post<Response>(this.productUrl, registering);
  }

  public async deleteProduct(product: Product): Promise<Response> {
    return await this.apiService.delete(`${this.productUrl}/${product.barcode}`);
  }

  public calculateCust(raws: RawQuantity[]): number {
    let cust = 0;
    raws.forEach((raw) => {
      cust += raw.raw.cust * raw.quantity;
    });
    return cust;
  }

  public calculatePrice(cust: number, custPerPricePercent: number): number {
    if (cust == 0) return 0;
    return cust / (custPerPricePercent / 100);
  }
}
