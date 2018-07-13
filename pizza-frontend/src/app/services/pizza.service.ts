import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";
import { Observable, onErrorResumeNext, of } from "rxjs";
import { map, filter, switchMap, catchError } from "rxjs/operators";
import { Pizza } from "../component/pizza/pizza";

@Injectable({
  providedIn: "root"
})
export class PizzaService {
  private handleError(error: any): Observable<any> {
    return Observable.throw(console.error("Some error occured", error));
  }
  private baseUrl = "http://localhost:8181";

  constructor(private http: Http) {}

  getPizzas(): Observable<Pizza[]> {
    return this.http.get(this.baseUrl + "/pizza/").pipe(
      map(response => {
        return <Pizza[]>response.json();
      }, catchError(this.handleError))
    );
  }

  createPizza(pizzaData: Pizza): Observable<Pizza> {
    return this.http.post(this.baseUrl + "/pizza/", pizzaData).pipe(
      map((response: Response) => {
        return <Pizza>response.json();
      }),
      catchError(this.handleError)
    );
  }

  deletePizza(id: string): Observable<any> {
    return this.http
      .delete(this.baseUrl + "/pizza/" + id)
      .pipe(catchError(this.handleError));
  }

  updatePizza(pizza: Pizza, price: number): Observable<Pizza> {
    return this.http.patch(this.baseUrl + "/pizza/" + pizza.id, price).pipe(
      map((response: Response) => {
        return <Pizza>response.json();
      }),
      catchError(this.handleError)
    );
  }
}
