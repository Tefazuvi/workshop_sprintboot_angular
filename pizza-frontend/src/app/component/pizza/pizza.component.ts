import { Component, OnInit } from '@angular/core';
import {
  FormControl,FormBuilder,FormGroup, Validators
} from '@angular/forms';
import { Observable } from 'rxjs';
import { Pizza } from './pizza';
import { PizzaService } from '../../services/pizza.service';
@Component({
  selector: 'app-pizza',
  templateUrl: './pizza.component.html',
  styleUrls: ['./pizza.component.css']
})
export class PizzaComponent implements OnInit {

  pizzaForm: FormGroup; 
  pizzas$: Observable<Pizza[]>;
  editing: boolean = false;
  editingPizza: Pizza = new Pizza();

  constructor(private fb: FormBuilder, private pizzaService: PizzaService) { 
    this.createForm();
    this.pizzas$ = pizzaService.getPizzas();
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
