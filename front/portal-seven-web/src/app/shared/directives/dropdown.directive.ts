import { Directive, HostListener, HostBinding } from '@angular/core';

@Directive({
  selector: '[app-dropdown]'
})
export class DropdownDirective {

  constructor() { }

  @HostBinding('class.open') isOpen = false;

  @HostListener('click') toggleOpen() {
    this.isOpen = !this.isOpen;
  }

  @HostListener('mouseleave') toggleasdOpen() {
    this.isOpen = false;
  }
}
