import { Input, Output, EventEmitter, Directive } from '@angular/core';

@Directive() // sem selector, só pra permitir decorators em herança
export abstract class DialogBase {
  @Input() visible = false;
  @Output() onClose = new EventEmitter<void>();

  public isAddVisible = false;

  public close() {
    this.onClose.emit();
  }

  public openAddModal() {
    this.isAddVisible = true;
  }

  public closeAddModal() {
    this.isAddVisible = false;
    this.onCloseAddModal();
  }

  protected onCloseAddModal() {}
}
