import { Directive, ElementRef, Renderer2, Input  } from '@angular/core';

@Directive({
  selector: '[appBalanceColor]'
})
export class BalanceColorDirective {
  @Input('appBalanceColor') set appBalanceColor(balance: number) {
    if(balance<0){
      this.renderer.setStyle(this.el.nativeElement, 'color', 'red');
    } else {
      this.renderer.setStyle(this.el.nativeElement, 'color', 'green');
    }
  }

  constructor(private el: ElementRef, private renderer: Renderer2) { }

}
