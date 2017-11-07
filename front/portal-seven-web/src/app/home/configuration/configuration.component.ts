import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfigurationService } from './services/configuration.service';
import { Configuration } from './models/configuration.model';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css']
})
export class ConfigurationComponent implements OnInit {

  constructor(private configurationService: ConfigurationService, private router:Router) { }

  backOffice:string;

  ngOnInit() {
    this.configurationService.getConfiguration().then((conf:Configuration)=>{
      console.log(conf);
      this.backOffice = conf.backOffice;
    })
  }

  formValid(form:NgForm){
    if (!form.valid) return false;

    console.log(form);

    return true;
  }

  onSubmit(form:NgForm){
    this.configurationService.saveConfiguration(<Configuration>form.value);
  }

  onCancel(){
    this.router.navigate(['home/hotel-offer']);
  }
}
