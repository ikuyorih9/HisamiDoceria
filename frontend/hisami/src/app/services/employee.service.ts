import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Role } from '../models/role.model';
import { AuthenticatedUser } from '../models/auth.model';
import { Employee } from '../models/employee.model';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  readonly userRoleUrl = 'http://localhost:8080/api/employees/role';
  readonly employeeAccountUrl = 'http://localhost:8080/api/employees';

  private readonly userInfoUrl = 'http://localhost:8080/auth/me';
  constructor(private apiService: ApiService) {}

  public async getEmployeeRole(username: string): Promise<Role> {
    return (await this.apiService.get(`${this.userRoleUrl}/${username}`)) as Role;
  }

  public async getEmployee(): Promise<Employee> {
    const user: AuthenticatedUser = await this.apiService.get(this.userInfoUrl);
    return await this.apiService.get(`${this.employeeAccountUrl}/${user.name}`);
  }
}
