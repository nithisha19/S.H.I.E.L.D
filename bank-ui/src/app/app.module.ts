import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { LoginComponent } from './login/login.component';
import { CreateaccountComponent } from './createaccount/createaccount.component';
import { CreateuserComponent } from './createuser/createuser.component';

import { ViewflaggedtransactionsComponent } from './viewflaggedtransactions/viewflaggedtransactions.component';
import { TitlebarComponent } from './titlebar/titlebar.component';
import { ViewaccountsComponent } from './viewaccounts/viewaccounts.component';
import { HttpClientModule } from '@angular/common/http';
import { ViewusersComponent } from './viewusers/viewusers.component';

import { ModifyuserComponent } from './modifyuser/modifyuser.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HomeComponent } from './home/home.component';



@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SidebarComponent,
    LoginComponent,
    CreateaccountComponent,
    CreateuserComponent, 
 
    ViewflaggedtransactionsComponent,
    TitlebarComponent,
    ViewaccountsComponent,
    ViewusersComponent,
  
    ModifyuserComponent,
    HomeComponent,
    //NgxPaginationModule,
    // MatFormFieldModule
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule,
   
    RouterModule.forRoot([
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'createaccount',
        component: CreateaccountComponent
      },
      {
        path: 'createuser',
        component: CreateuserComponent
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
           
      {
        path:'sidebar',
        component:SidebarComponent
      },
      {
        path:'viewflaggedtransactions',
        component:ViewflaggedtransactionsComponent
      },   
      {
        path:'titlebar',
        component:TitlebarComponent
      },
      {
        path:'viewusers',
        component:ViewusersComponent
      
      },
      {
        path:'modifyuser/:id',
        component:ModifyuserComponent
      },
      {
        path:'viewaccounts',
        component:ViewaccountsComponent
      },
      {
        path:'home',
        component:HomeComponent
      }
     

    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
