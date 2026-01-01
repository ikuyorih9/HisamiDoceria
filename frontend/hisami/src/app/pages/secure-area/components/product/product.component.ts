import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ProductService } from '../../../../services/product.service';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CardModule } from 'primeng/card';
import { Raw, RawQuantity } from '../../../../models/raw.model';
import { InputNumberModule } from 'primeng/inputnumber';
import { MultiSelectModule } from 'primeng/multiselect';
import { RawService } from '../../../../services/raw.service';
import { Product } from '../../../../models/product.model';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

@Component({
  selector: 'app-product-component',
  templateUrl: '/product.component.html',
  styleUrl: '/product.component.scss',
  imports: [
    CommonModule,
    FormsModule,
    DialogModule,
    ButtonModule,
    CardModule,
    ReactiveFormsModule,
    InputNumberModule,
    MultiSelectModule,
    InputTextModule,
    ConfirmDialogModule,
    ToastModule,
  ],
  providers: [ConfirmationService, MessageService],
})
export class ProductComponent implements OnInit {
  @Input() visible: boolean = false;
  @Output() onClose = new EventEmitter<void>();

  public rawOptions: RawQuantity[] = [];
  public form!: FormGroup;
  public products: Product[] = [];
  public isAddVisible = false;

  public imageBase64: string | null = null; // sem o prefixo "data:image/..;base64,"

  public selectedFileName: String = '';

  constructor(
    private productService: ProductService,
    private rawService: RawService,
    private fb: FormBuilder,
    private confirmationService: ConfirmationService,
  ) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      description: [''],
      price: [0, [Validators.required, Validators.min(0)]],
      cust: [0, [Validators.required, Validators.min(0)]],
      percentCustPrice: [0, [Validators.required, Validators.min(0), Validators.max(100)]],
      barcode: ['', [Validators.required]],
      raws: [[] as RawQuantity[], [Validators.required]],
    });
  }

  public async ngOnInit(): Promise<void> {
    await this.refresh();
    console.log('Products: ', this.products);
    const raws = await this.rawService.getAllRaws();
    this.rawOptions = raws.map((raw) => {
      return {
        raw: raw,
        quantity: 1,
      } as RawQuantity;
    });
  }

  public async refresh() {
    const data = await this.productService.getProducts();
    this.products = [...data];
  }

  public close() {
    this.onClose.emit();
  }

  public openAddModal() {
    this.isAddVisible = true;
  }

  public closeAddModal() {
    this.isAddVisible = false;
    this.resetForm();
  }

  public resetForm(): void {
    this.form.reset({
      name: '',
      description: '',
      price: 0,
      cust: 0,
      percentCustPrice: 0,
      barcode: '',
      raws: [] as Raw[],
    });

    this.form.markAsPristine();
    this.form.markAsUntouched();
    this.form.updateValueAndValidity();
  }

  public async submit() {
    if (this.form.invalid) {
      console.log('invalid form');
      this.form.markAllAsTouched();
      return;
    }

    const product: Product = this.form.getRawValue() as Product;
    product.cust = this.productService.calculateCust(product.raws);
    product.price = this.productService.calculatePrice(product.cust, product.percentCustPrice);
    product.image = this.imageBase64; // string base64
    const response = await this.productService.productRegistering(product);
    alert(response.text);
    await this.refresh();
    this.closeAddModal();
  }

  public isInvalid(field: string): boolean {
    const c = this.form.get(field);
    return !!(c && c.invalid && (c.dirty || c.touched));
  }

  public updateQuantity(index: number, quantity: number) {
    const raws = [...this.selectedRaws]; // copia o array
    raws[index] = { ...raws[index], quantity }; // copia o item e altera

    this.form.get('raws')!.setValue(raws); // atualiza o form
    this.form.get('raws')!.markAsDirty();
  }

  public normalizeQty(i: number) {
    const current = this.selectedRaws[i]?.quantity;
    if (current == null || current < 1) {
      this.updateQuantity(i, 1); // volta para 1 se ficou vazio ou 0
    }
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) return;

    const file = input.files[0];
    this.selectedFileName = file?.name ?? '';

    const reader = new FileReader();
    reader.onload = () => {
      const full = reader.result as string; // ex: "data:image/png;base64,AAAA..."
      this.imageBase64 = full.split(',')[1]; // pega só o base64 puro
    };
    reader.readAsDataURL(file);
  }

  public async deleteProduct(event: Event, product: Product) {
    this.confirmationService.confirm({
      target: event.target as EventTarget,
      message: 'Você deseja mesmo deletar o produto?',
      header: 'Deletar produto',
      closable: true,
      closeOnEscape: true,
      icon: 'pi pi-exclamation-triangle',
      acceptButtonProps: {
        label: 'Confirmar',
      },
      rejectVisible: false,
      accept: async () => {
        await this.productService.deleteProduct(product);
        await this.refresh();
      },
      reject: () => {},
    });
  }

  get selectedRaws(): any[] {
    return this.form.get('raws')?.value ?? [];
  }
}
