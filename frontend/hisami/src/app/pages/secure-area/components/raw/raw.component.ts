import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { CardModule } from 'primeng/card';
import { RawService } from '../../../../services/raw.service';
import { Raw } from '../../../../models/raw.model';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
@Component({
  selector: 'app-raw-component',
  templateUrl: '/raw.component.html',
  imports: [CommonModule, DialogModule, ButtonModule, CardModule, ReactiveFormsModule],
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
    this.raws = await this.rawService.getAllRaws();
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

  public close() {
    this.onClose.emit();
  }

  public openAddModal() {
    this.isAddVisible = true;
  }

  public closeAddModal() {
    this.isAddVisible = false;
  }
}
