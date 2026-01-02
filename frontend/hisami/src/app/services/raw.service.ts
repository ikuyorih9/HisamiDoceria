import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Raw } from '../models/raw.model';

@Injectable({ providedIn: 'root' })
export class RawService {
  readonly rawUrl = 'http://localhost:8080/api/raw';
  constructor(private apiService: ApiService) {}

  public async getAllRaws(): Promise<Raw[]> {
    return await this.apiService.get(this.rawUrl);
  }

  public async addRaw(raw: Raw): Promise<Response> {
    return await this.apiService.post<Response, Raw>(this.rawUrl, raw);
  }

  public async deleteRaw(raw: Raw): Promise<Response> {
    return await this.apiService.delete(`${this.rawUrl}/${raw.name}`);
  }

  public async editRaw(raw: Raw) {
    return await this.apiService.post<Response>(`${this.rawUrl}/${raw.name}`, raw);
  }
}
