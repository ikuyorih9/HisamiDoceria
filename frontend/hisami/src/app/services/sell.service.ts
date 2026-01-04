import { Injectable } from '@angular/core';
import { Sell, SellBodyRequest } from '../models/sell.model';
import { ApiService } from './api.service';
import { ApiBase } from '../interfaces/api-base.interface';
import { Response } from '../models/response.model';

@Injectable({ providedIn: 'root' })
export class SellService extends ApiBase {
  readonly sellUrl = 'http://localhost:8080/api/sell';

  constructor() {
    super();
  }

  public async getSells(): Promise<Sell[]> {
    return this.apiService.get(this.sellUrl);
  }

  public async registerSells(sells: SellBodyRequest[]): Promise<void> {
    sells.forEach(async (s) => {
      await this.apiService.post<Response>(this.sellUrl, s);
    });
  }
}
