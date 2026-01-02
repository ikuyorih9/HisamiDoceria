import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';
import { Raw } from '../../../../models/raw.model';
import { RawService } from '../../../../services/raw.service';

@Component({
  selector: 'app-stock-raw-component',
  templateUrl: '/stock-raw.component.html',
  styleUrl: '/stock-raw.component.scss',
  imports: [CommonModule, FormsModule, InputNumberModule],
})
export class StockRawComponent implements OnInit {
  @Input() isEditable: boolean = false;
  public raws: Raw[] = [];
  constructor(private rawService: RawService) {}

  async ngOnInit() {
    await this.refresh();
  }

  async refresh() {
    const data = await this.rawService.getAllRaws();
    this.raws = [...data];
  }

  public async update(raw: Raw) {
    console.log(this.raws);
    await this.rawService.editRaw(raw);
    await this.refresh();
  }
}
