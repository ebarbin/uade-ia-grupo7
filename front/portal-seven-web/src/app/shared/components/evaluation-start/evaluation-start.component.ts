import { EvaluationStartService } from './evaluation-start.service';
import { Component, Input } from '@angular/core';
import { Constant } from '../../models/constant';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-evaluation-start',
  templateUrl: './evaluation-start.component.html',
  styleUrls: ['./evaluation-start.component.css']
})
export class EvaluationStartComponent {

  @Input()valoration:number;
  @Input()type:string;
  @Input()id:number;
  @Input()disabled:boolean;
  
  constructor(
    private toastr:ToastrService,
    private evaluationStartService:EvaluationStartService) { }

  onMarkFirstStar(){
    this.calculate(1);
  }
  
  onMarkSecondStar(){
    this.calculate(2);
  }
  
  onMarkThirdStar(){
    this.calculate(3);
  }

  onMarkFourthStar(){
    this.calculate(4);
  }
  
  onMarkFifthStar(){
    this.calculate(5);
  }

  private calculate(value){
    if (this.type == Constant.HOTEL) {
      this.evaluationStartService.hotelValoration(this.id, value).then((result:number)=>{
        this.valoration = result;
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
    } else if (this.type == Constant.PACKAGE){
      this.evaluationStartService.packageValoration(this.id, value).then((result:number)=>{
        this.valoration = result;
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
    }

  }

}
