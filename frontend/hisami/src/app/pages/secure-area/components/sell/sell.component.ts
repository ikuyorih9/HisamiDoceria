import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DialogBase } from '../../interfaces/dialog.interface';
import { DialogModule } from 'primeng/dialog';
import { Product } from '../../../../models/product.model';
import { ProductService } from '../../../../services/product.service';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Sell, SellBodyRequest } from '../../../../models/sell.model';
import { SellService } from '../../../../services/sell.service';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { EmployeeService } from '../../../../services/employee.service';
import { Employee } from '../../../../models/employee.model';
import { MultiSelectModule } from 'primeng/multiselect';

export type ProductQuantity = {
  product: Product;
  quantity: number;
};

export interface SellGroup {
  dateTime: Date;
  items: Sell[];
  total: number;
}

@Component({
  selector: 'app-sell-component',
  templateUrl: '/sell.component.html',
  styleUrl: '/sell.component.scss',
  imports: [
    CommonModule,
    FormsModule,
    DialogModule,
    InputNumberModule,
    ReactiveFormsModule,
    CardModule,
    MultiSelectModule,
    ButtonModule,
  ],
})
export class SellComponent extends DialogBase implements OnInit {
  public products: Product[] = [];
  public selectedProducts: ProductQuantity[] = [];
  public sells: Sell[] = [];
  groupedSells: SellGroup[] = [];

  constructor(
    private productService: ProductService,
    private sellService: SellService,
    private employeeService: EmployeeService,
  ) {
    super();
  }

  async ngOnInit() {
    const _product = await this.productService.getProducts();
    this.products = [..._product];

    this.refresh();
  }

  async refresh() {
    const _sell = await this.sellService.getSells();
    this.sells = [..._sell];
    this.setSells(_sell);
  }

  public onSelected(event: any) {
    const response = event.value as Product[];
    const selected = response.map((p) => {
      return {
        product: p,
        quantity: 1,
      } as ProductQuantity;
    });

    this.selectedProducts = [...selected];
  }

  public async registerSells() {
    const dateTime: Date = new Date();
    const employee: Employee = await this.employeeService.getEmployee();
    const apiBody = this.selectedProducts.map((p) => {
      return {
        cpf: employee.cpf,
        barcode: p.product.barcode,
        dateTime: dateTime,
        quantity: p.quantity,
      } as SellBodyRequest;
    });
    console.log(apiBody);
    this.sellService.registerSells(apiBody);
  }

  get totalPrice() {
    let totalPrice = 0;
    this.selectedProducts.forEach((s) => {
      totalPrice += s.product.price * s.quantity;
    });
    return totalPrice;
  }

  private groupSellsByDateTimeSecond(sells: Sell[]): SellGroup[] {
    const map = new Map<string, SellGroup>();

    for (const s of sells) {
      const dt = s.dateTime instanceof Date ? s.dateTime : new Date(s.dateTime as any);

      // chave por segundo (ignora ms)
      const key =
        [
          dt.getFullYear(),
          String(dt.getMonth() + 1).padStart(2, '0'),
          String(dt.getDate()).padStart(2, '0'),
        ].join('-') +
        'T' +
        [
          String(dt.getHours()).padStart(2, '0'),
          String(dt.getMinutes()).padStart(2, '0'),
          String(dt.getSeconds()).padStart(2, '0'),
        ].join(':');

      if (!map.has(key)) {
        map.set(key, { dateTime: dt, items: [], total: 0 });
      }

      const g = map.get(key)!;
      g.items.push(s);
      g.total += (s.product?.price ?? 0) * (s.quantity ?? 1);
    }

    return Array.from(map.values()).sort((a, b) => b.dateTime.getTime() - a.dateTime.getTime());
  }

  // chame quando carregar sells
  setSells(sells: Sell[]) {
    this.sells = sells;
    this.groupedSells = this.groupSellsByDateTimeSecond(sells);
  }
}
