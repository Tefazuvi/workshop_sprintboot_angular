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
  pizzas: Pizza[];

  constructor(private fb: FormBuilder, private pizzaService: PizzaService) { 
    this.createForm();
    pizzaService.getPizzas().subscribe(pizzas => {
      this.pizzas = pizzas;
    })
  }

  createForm() {
    this.pizzaForm = this.fb.group({
      name: ['', Validators.required ],
      price: ['', Validators.required ],
    });
  }

  ngOnInit() {
  }

  deletePizza(id: string): void {
    
    this.pizzaService.deletePizza(id)
    .subscribe(
      () => {
        this.pizzas = this.pizzas.filter(pizza => pizza.id != id);
      },
      error => console.log("Error :: " + error)
    )
  }

  editPizza(pizza: Pizza): void {
    this.editing = true;
    Object.assign(this.editingPizza, pizza); 
    console.log(this.editingPizza);
  }


  updatePizza(pizza: Pizza): void {
    this.pizzaService.updatePizza(pizza,pizza.price)
    .subscribe(updatedPizza => {
      console.log(updatedPizza);
      let existingTodo = this.pizzas.find(pizza => pizza.id === updatedPizza.id);
      Object.assign(existingTodo, updatedPizza);
      this.clearEditing();
    });
  }


  clearEditing(): void {
    this.editingPizza = new Pizza();
    this.editing = false;
  }


}
