import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-menu-page',
  templateUrl: '/menu.page.html',
  styleUrl: '/menu.page.scss',
  imports: [CommonModule, CardModule],
})
export class MenuPage implements OnInit {
  public products: Product[] = [];

  constructor(private productService: ProductService) {}

  public async ngOnInit() {
    const _product = await this.productService.getProducts();
    this.products = [..._product];
    console.log(this.products);
  }
}
