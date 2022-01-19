package com.qa.pizzeria;

import java.util.ArrayList;
import java.util.List;

public class Runner {
	public static void main(String[] args) {

		JDBC_Setup setup = new JDBC_Setup();
		setup.connect();

		Pizza tuna = new Pizza("Tuna", 8, false);
		Pizza chicken = new Pizza("Chicken", 12, true);
		PizzaManager manager = new PizzaManager();
		List<Pizza> pizza = new ArrayList<>();

//		pizza.add(tuna);
//		pizza.add(chicken);
//		manager.addMultiple(pizza);

//		manager.addPizza(tuna);
//		manager.addPizza(chicken);

//		System.out.println(manager.viewPizza(1));
//		System.out.println(manager.viewPizza(3));

		manager.viewAllPizzas();
		manager.deletePizzaSC(true);
		manager.viewAllPizzas();
	}

}
