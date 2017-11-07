import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css']
})
export class ConfigurationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  formValid(form:NgForm){
    if (!form.valid) return false;

    console.log(form);

    return true;
  }

  onSubmit(form:NgForm){
    console.log(form);
  }

  onCancel(){

  }
}
