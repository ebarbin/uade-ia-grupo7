import { EvaluationStartService } from './evaluation-start.service';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-evaluation-start',
  templateUrl: './evaluation-start.component.html',
  styleUrls: ['./evaluation-start.component.css']
})
export class EvaluationStartComponent {

  @Input()valoration:number;
  @Input()type:string;
  @Input()id:number;

  constructor(private evaluationStartService:EvaluationStartService) { }

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
      if (this.valoration == value) value = 0; 
      this.evaluationStartService.hotelValoration(this.id, value).then((result:number)=>{
        this.valoration = result;
      });
  }

}
