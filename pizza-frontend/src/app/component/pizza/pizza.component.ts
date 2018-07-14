import { Component, OnInit } from '@angular/core';
import {
  FormControl,FormBuilder,FormGroup, Validators, NgForm
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

  constructor(private fb: FormBuilder, private pizzaService: PizzaService) {}

  createForm() {
    this.pizzaForm = this.fb.group({
      name: ['', Validators.required ],
      price: ['', Validators.required ],
    });
  }

  ngOnInit() {
    this.createForm();
    this.pizzaService.getPizzas().subscribe(pizzas => {
      this.pizzas = pizzas;
    })
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
  }


  updatePizza(pizza: Pizza): void {
    this.pizzaService.updatePizza(pizza,pizza.price)
    .subscribe(updatedPizza => {
      let existingTodo = this.pizzas.find(pizza => pizza.id === updatedPizza.id);
      Object.assign(existingTodo, updatedPizza);
      this.clearEditing();
    });
  }


  clearEditing(): void {
    this.editingPizza = new Pizza();
    this.editing = false;
  }

  createPizza(pizzaForm: FormGroup): void {
    var pizza: Pizza = new Pizza();
    pizza.name = pizzaForm.get("name").value;
    pizza.price = pizzaForm.get("price").value;
    pizza.createdAt = new Date();
    this.pizzaService.createPizza(pizza).subscribe(newPizza => {
      this.pizzas.push(newPizza);
    })
    pizzaForm.reset();
  }
}
