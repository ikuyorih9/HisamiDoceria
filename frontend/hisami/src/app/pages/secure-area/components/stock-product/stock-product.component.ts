import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../../../../services/product.service';
import { Product } from '../../../../models/product.model';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-stock-product-component',
  templateUrl: '/stock-product.component.html',
  styleUrl: '/stock-product.component.scss',
  imports: [CommonModule, FormsModule, InputNumberModule],
})
export class StockProductComponent implements OnInit {
  @Input() isEditable: boolean = false;
  public products: Product[] = [];

  constructor(private productService: ProductService) {}
  async ngOnInit() {
    this.refresh();
  }

  private async refresh() {
    const data = await this.productService.getProducts();
    this.products = [...data];
  }

  public update(product: Product) {
    console.log(this.products);
    this.productService.editProduct(product);
    this.refresh();
  }
}
