import {Directive, ViewContainerRef} from '@angular/core';
@Directive({
  selector: '[appRef]'
})
export class ElementRefContainerDirective {
  constructor(public refContainer:ViewContainerRef) { }

}
