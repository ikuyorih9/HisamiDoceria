import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { AuthenticatedUser } from '../../models/auth.model';
import { Employee } from '../../models/employee.model';
import { CommonModule } from '@angular/common';
import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { RawComponent } from './components/raw/raw.component';
import { ProductComponent } from './components/product/product.component';
import { Role } from '../../models/role.model';
import { EmployeeService } from '../../services/employee.service';
import { StockProductComponent } from './components/stock-product/stock-product.component';
import { StockRawComponent } from './components/stock-raw/stock-raw.component';
import { SellComponent } from './components/sell/sell.component';

@Component({
  selector: 'app-secure-area',
  templateUrl: '/secure-area.page.html',
  styleUrl: '/secure-area.page.scss',
  imports: [
    CommonModule,
    PanelModule,
    ButtonModule,
    DialogModule,
    RawComponent,
    ProductComponent,
    StockProductComponent,
    StockRawComponent,
    SellComponent,
  ],
})
export class SecureAreaPage implements OnInit {
  private readonly userInfoUrl = 'http://localhost:8080/auth/me';
  private readonly employeeAccountUrl = 'http://localhost:8080/api/employees';

  public employee: Employee | null = null;
  public role: Role | null = null;
  public isDialogRawVisible: boolean = false;
  public isDialogProductVisible: boolean = false;
  public isDialogSellVisible: boolean = false;
  public isStockProductEditable: boolean = false;
  public isStockRawEditable: boolean = false;

  constructor(
    private apiService: ApiService,
    private employeeService: EmployeeService,
  ) {}

  async ngOnInit(): Promise<void> {
    const user: AuthenticatedUser = await this.apiService.get(this.userInfoUrl);
    this.employee = await this.apiService.get(`${this.employeeAccountUrl}/${user.name}`);
    console.log('Loading employee...', this.employee);
    this.role = await this.employeeService.getEmployeeRole(user.name);
    console.log('Loading role... ', this.role);
  }

  public async showRawDialog() {
    this.isDialogRawVisible = true;
  }

  public closeRawDialog() {
    this.isDialogRawVisible = false;
  }

  public async showProductDialog() {
    this.isDialogProductVisible = true;
  }

  public closeProductDialog() {
    this.isDialogProductVisible = false;
  }

  public async showSellDialog() {
    this.isDialogSellVisible = true;
  }

  public closeSellDialog() {
    this.isDialogSellVisible = false;
  }

  public changeStockProductEditable() {
    this.isStockProductEditable = !this.isStockProductEditable;
  }

  public changeStockRawEditable() {
    this.isStockRawEditable = !this.isStockRawEditable;
  }
}
