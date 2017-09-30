import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import { UserProfileComponent } from './user-profile.component';
import { ChangeImageComponent } from './components/change-image/change-image.component';
import { NewUserComponent } from './components/new-user/new-user.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [
    UserProfileComponent,
    ChangeImageComponent,
    NewUserComponent,
  ],
  entryComponents:[ChangeImageComponent,NewUserComponent],
})
export class UserProfileModule { }
