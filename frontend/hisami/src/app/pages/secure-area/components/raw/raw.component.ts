import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { CardModule } from 'primeng/card';
import { RawService } from '../../../../services/raw.service';
import { Raw } from '../../../../models/raw.model';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService, MessageService } from 'primeng/api';
@Component({
  selector: 'app-raw-component',
  templateUrl: '/raw.component.html',
  imports: [
    CommonModule,
    DialogModule,
    ButtonModule,
    CardModule,
    ReactiveFormsModule,
    ConfirmDialogModule,
    ToastModule,
  ],
  providers: [ConfirmationService, MessageService],
})
export class RawComponent implements OnInit {
  @Input() visible: boolean = false;
  @Output() onClose = new EventEmitter<void>();

  public raws: Raw[] = [];
  public isAddVisible = false;
  public rawForm!: FormGroup;

  constructor(
    private rawService: RawService,
    private fb: FormBuilder,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private cdr: ChangeDetectorRef,
  ) {
    this.rawForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(1)]],
      cust: [null as number | null, [Validators.required, Validators.min(0.01)]],
    });
  }

  async ngOnInit() {
    this.refresh();
  }

  async refresh() {
    const data = await this.rawService.getAllRaws();
    this.raws = [...data];
  }

  async submit() {
    if (this.rawForm.invalid) {
      this.rawForm.markAllAsTouched();
      return;
    }
    const payload = this.rawForm.getRawValue();
    await this.rawService.addRaw(payload);
    this.refresh();
    this.closeAddModal();
  }

  trackByRaw(index: number, raw: { name: string; cust: number }) {
    return `${raw.name}__${raw.cust}__${index}`;
  }

  public close() {
    this.onClose.emit();
  }

  public async deleteRaw(event: Event, raw: Raw) {
    this.confirmationService.confirm({
      target: event.target as EventTarget,
      message: 'Você deseja mesmo deletar o ingrediente?',
      header: 'Deletar ingrediente',
      closable: true,
      closeOnEscape: true,
      icon: 'pi pi-exclamation-triangle',
      acceptButtonProps: {
        label: 'Confirmar',
      },
      rejectVisible: false,
      accept: async () => {
        await this.rawService.deleteRaw(raw);
        await this.refresh();
      },
      reject: () => {},
    });
  }

  public openAddModal() {
    this.isAddVisible = true;
  }

  public closeAddModal() {
    this.isAddVisible = false;
  }
}
