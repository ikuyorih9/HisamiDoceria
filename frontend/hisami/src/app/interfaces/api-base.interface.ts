import { Directive, inject } from '@angular/core';
import { ApiService } from '../services/api.service';

@Directive() // sem selector, só pra permitir decorators em herança
export abstract class ApiBase {
  protected apiService = inject(ApiService);
}
