import { Component, OnInit } from '@angular/core';
import {
  FormControl,FormBuilder,FormGroup, Validators
} from '@angular/forms';
@Component({
  selector: 'app-pizza',
  templateUrl: './pizza.component.html',
  styleUrls: ['./pizza.component.css']
})
export class PizzaComponent implements OnInit {

  pizzaForm: FormGroup; 

  constructor(private fb: FormBuilder) { 
    this.createForm();
  }

  createForm() {
    this.pizzaForm = this.fb.group({
      name: ['', Validators.required ],
      price: ['', Validators.required ],
    });
  }

  ngOnInit() {
  }

}
