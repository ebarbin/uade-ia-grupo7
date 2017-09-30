import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import { UserProfileComponent } from './user-profile.component';
import { ChangeImageComponent } from './components/change-image/change-image.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [
    UserProfileComponent,
    ChangeImageComponent,
  ],
  entryComponents:[ChangeImageComponent],
})
export class UserProfileModule { }
