import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Role } from '../models/role.model';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  readonly userRoleUrl = 'http://localhost:8080/api/employees/role';
  constructor(private apiService: ApiService) {}

  public async getEmployeeRole(username: string): Promise<Role> {
    return (await this.apiService.get(`${this.userRoleUrl}/${username}`)) as Role;
  }
}
