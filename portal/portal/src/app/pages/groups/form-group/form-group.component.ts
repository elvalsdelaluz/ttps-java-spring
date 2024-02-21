import { Component } from '@angular/core';

@Component({
  selector: 'app-form-group',
  templateUrl: './form-group.component.html',
  styleUrl: './form-group.component.css'
})
export class FormGroupComponent {

  addGroup(){
    console.log("Agregando grupo");
    return false;
  }

}
