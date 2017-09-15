import { Observable } from 'rxjs/Observable';
import { DataSource } from '@angular/cdk/collections';

export class CustomDatasource extends DataSource<any> {
    
  length: number = 0;
  
  constructor(private data: any[]){
    super();
    this.length = data.length;
  }

  connect(): Observable<any[]> {
      return Observable.of(this.data);
  }
  disconnect() {}
}