import { Component, ViewChild } from "@angular/core";
import { Carousel, CarouselModule } from 'primeng/carousel';
import { PRODUCTS } from "../../constants/mock/product.constant.mock";
import { Product } from "../../interfaces/product";
import { ButtonModule } from "primeng/button";
import { CardComponent } from "../../components/card/card.component";

@Component({
    selector: 'home-page',
    templateUrl: './home.page.html',
    styleUrl: './home.page.scss',
    imports:[CarouselModule, ButtonModule, CardComponent]
})
export class HomePage{
    public products: Product[] = PRODUCTS;

    @ViewChild('car') car!: Carousel;

next() {
  this.car.navForward(null as any); // vai para o próximo
}
}