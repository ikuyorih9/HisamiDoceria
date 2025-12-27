import { Component, Input } from "@angular/core";

@Component({
    selector: 'app-card',
    templateUrl: './card.component.html',
    styleUrl: './card.component.scss',
    imports: []
})
export class CardComponent {
    @Input() image: string | null = null;
    @Input() title: string  = 'Title';
    @Input() description: string  = 'Description';
    @Input() price: number = 0;
    @Input() unity: string = 'R$/un'
    @Input() height: number | null = null; 

}