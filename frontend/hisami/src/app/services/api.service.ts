import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  constructor(private http: HttpClient) {}

  public get<T>(
    url: string,
    options?: {
      params?: Record<string, string | number | boolean>;
      headers?: Record<string, string>;
    }
  ): Promise<T> {
    let params = new HttpParams();
    if (options?.params) {
      for (const [key, value] of Object.entries(options.params)) {
        params = params.set(key, String(value));
      }
    }

    let headers = new HttpHeaders();
    if (options?.headers) {
      for (const [key, value] of Object.entries(options.headers)) {
        headers = headers.set(key, value);
      }
    }

    return firstValueFrom(this.http.get<T>(url, { params, headers }));
  }

  public post<TResponse, TBody = unknown>(
    url: string,
    body: TBody,
    options?: {
      params?: Record<string, string | number | boolean>;
      headers?: Record<string, string>;
    }
  ): Promise<TResponse> {
    let params = new HttpParams();
    if (options?.params) {
      for (const [key, value] of Object.entries(options.params)) {
        params = params.set(key, String(value));
      }
    }

    let headers = new HttpHeaders();
    if (options?.headers) {
      for (const [key, value] of Object.entries(options.headers)) {
        headers = headers.set(key, value);
      }
    }

    return firstValueFrom(this.http.post<TResponse>(url, body, { params, headers }));
  }
}
