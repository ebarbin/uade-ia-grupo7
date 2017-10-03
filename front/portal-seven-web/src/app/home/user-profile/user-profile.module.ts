import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import { UserProfileComponent } from './user-profile.component';
import { NewUserComponent } from './components/new-user/new-user.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [
    UserProfileComponent,
    NewUserComponent,
  ],
  entryComponents:[NewUserComponent],
})
export class UserProfileModule { }
