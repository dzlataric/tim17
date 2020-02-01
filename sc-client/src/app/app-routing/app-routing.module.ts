import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import {MagazineComponent} from '../magazine/magazine.component';
import {SelectedMagazineComponent} from '../magazine/selected-magazine.component';

const routes: Routes = [
  {
    path: 'magazine',
    component: MagazineComponent,
  },
  {
    path: 'magazine/:magazineId',
    component: SelectedMagazineComponent,
  },
  { path: '',
    redirectTo: 'magazine',
    pathMatch: 'full'
  },

];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
