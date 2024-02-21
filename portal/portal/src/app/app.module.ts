import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListGroupsComponent } from './pages/groups/list-groups/list-groups.component';
import { FormGroupComponent } from './pages/groups/form-group/form-group.component';
import { FormBillComponent } from './pages/bills/form-bill/form-bill.component';
import { FormPayComponent } from './pages/bills/form-pay/form-pay.component';
import { ListBillsComponent } from './pages/bills/list-bills/list-bills.component';
import { NavComponent } from './shared/nav/nav.component';
import { DashboardComponent } from './shared/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    ListGroupsComponent,
    FormGroupComponent,
    FormBillComponent,
    FormPayComponent,
    ListBillsComponent,
    NavComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
