import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';

import {

  MatButtonModule,
  MatIconModule,
  MatBadgeModule,
  MatCardModule,
  MatProgressSpinnerModule,
  MatTooltipModule,
  MatInputModule,
  MatSnackBarModule,
  MatGridListModule,
  MatToolbarModule,
  MatSelectModule,
  MatOptionModule,
  MatDialogModule,
  MatSlideToggleModule,

} from '@angular/material';
import { ProfileComponent } from './profile/profile.component';
import { CreateQrComponent } from './create-qr/create-qr.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CacheInterceptor } from './interceptor/cache-interceptor';
import { HistoryComponent } from './history/history.component';
import { LayoutComponent } from './layout/layout.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { FormsService } from './services/forms.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { DialogOpener } from './components/dialog/dialog.opener';
import { DialogComponent } from './components/dialog/dialog.component';
import { AuthGuard } from './guards/auth.guard';
import { CreateSolicitudComponent } from './components/create/create-solicitud.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    ProfileComponent,
    CreateQrComponent,
    HistoryComponent,

    LayoutComponent,
    RegisterComponent,
    LoginComponent,
    DialogComponent,
    CreateSolicitudComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,

    MatButtonModule,
    MatIconModule,
    MatBadgeModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatTooltipModule,
    MatInputModule,
    MatSnackBarModule,
    FormsModule,
    HttpClientModule,
    MatGridListModule,
    MatToolbarModule,
    MatSelectModule,
    MatOptionModule,
    MatDialogModule,
    MatSlideToggleModule,

    ReactiveFormsModule,
    FlexLayoutModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: CacheInterceptor, multi: true
    },
    FormsService,
    DialogOpener,
    AuthGuard
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    DialogComponent
  ]
})
export class AppModule { }
