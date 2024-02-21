import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListGroupsComponent } from './pages/groups/list-groups/list-groups.component';
import { ListBillsComponent } from './pages/bills/list-bills/list-bills.component';
import { FormGroupComponent } from './pages/groups/form-group/form-group.component';

const routes: Routes = [
  {path:'groups',component:ListGroupsComponent},
  {path:'bills',component:ListBillsComponent},
  {path:'form-group',component:FormGroupComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
