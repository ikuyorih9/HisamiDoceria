import { Component } from "@angular/core";
import { ButtonModule} from 'primeng/button'

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrl: './header.component.scss',
    imports: [ButtonModule]
})
export class HeaderComponent{}