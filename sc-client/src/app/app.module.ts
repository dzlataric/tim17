import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MagazineComponent } from './magazine/magazine.component';
import { ProjectComponent } from './project/project.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { SelectedMagazineComponent } from './magazine/selected-magazine.component';

@NgModule({
  declarations: [
    AppComponent,
    MagazineComponent,
    ProjectComponent,
    SelectedMagazineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
