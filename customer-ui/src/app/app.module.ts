import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// import { UserIdleModule } from 'angular-user-idle';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TitlebarComponent } from './titlebar/titlebar.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgxPaginationModule } from 'ngx-pagination';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ProfileComponent } from './profile/profile.component';
import { BalanceComponent } from './balance/balance.component';
import { TransactionComponent } from './transaction/transaction.component';
import { AccountComponent } from './account/account.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { TransferComponent } from './transfer/transfer.component';
import { NgxDaterangepickerMd } from 'ngx-daterangepicker-material';
import { MinistatementComponent } from './ministatement/ministatement.component';
@NgModule({
  declarations: [
    AppComponent,
    TitlebarComponent,
    RegisterComponent,
    LoginComponent,
    DashboardComponent,
    SidebarComponent,
    ProfileComponent,
    BalanceComponent,
    TransactionComponent,
    AccountComponent,
    HomeComponent,
    TransferComponent,
    MinistatementComponent
  ],
  imports: [
    BrowserModule,
    NgxPaginationModule,
    AppRoutingModule,
    FormsModule,
    UserIdleModule.forRoot({ idle: 6, timeout: 5, ping: 4 }),
    HttpClientModule,
    NgxDaterangepickerMd.forRoot(
    ),
    RouterModule.forRoot([
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'profile',
        component: ProfileComponent
      },
      {
        path: 'balance',
        component: BalanceComponent
      },
      {
        path: 'transaction/:id',
        component: TransactionComponent
      },
      {
        path: 'account',
        component: AccountComponent
      },
      {
        path: 'transfer/:id',
        component: TransferComponent
      },
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'ministatement/:id',
        component: MinistatementComponent
      },
      {
        path: '**',
        component: HomeComponent
      },

    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
