import { Component, OnInit, Inject } from '@angular/core';
import { MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  title:string;
  message:string;

  constructor(
    private dialogRef: MdDialogRef<ConfirmComponent>,
    @Inject(MD_DIALOG_DATA) private data: {title:string, message:string}) { 
      this.title = data.title;
      this.message = data.message;
  }

  ngOnInit() {}

  onNo(){
    this.dialogRef.close(false);
  }

  onYes(){
    this.dialogRef.close(true);
  }
}
