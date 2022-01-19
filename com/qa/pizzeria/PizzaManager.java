package com.qa.pizzeria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PizzaManager {
	static Statement stmt;
	static Connection conn;
	JDBC_Setup jdbc = new JDBC_Setup();

	// Add a Pizza object to database
	public void addPizza(Pizza pizza) {
		try {
			conn = jdbc.connect();
//			stmt = conn.createStatement();
//			String query = "INSERT INTO pizzas (type, slices, stuffedCrust) VALUES('" + pizza.getType() + "', "
//					+ pizza.getSlices() + ", " + pizza.isStuffedCrust() + ")";
//			stmt.executeUpdate(query);
			PreparedStatement preStmt = conn
					.prepareStatement("INSERT INTO pizzas (type, slices, stuffedCrust) VALUES(?, ?, ?)");
			preStmt.setString(1, pizza.getType());
			preStmt.setInt(2, pizza.getSlices());
			preStmt.setBoolean(3, pizza.isStuffedCrust());
			preStmt.executeUpdate();
			System.out.println(pizza.getType() + " pizza added to database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Add multiple Pizza objects to DB via ArrayList
	public void addMultiple(List<Pizza> pizza) {
		for (int i = 0; i < pizza.size(); i++) {
			try {
				conn = jdbc.connect();
//				stmt = conn.createStatement();
//				String query = "INSERT INTO pizzas (type, slices, stuffedCrust) VALUES('" + pizza.get(i).getType()
//						+ "', " + pizza.get(i).getSlices() + ", " + pizza.get(i).isStuffedCrust() + ")";
//				stmt.executeUpdate(query);
				PreparedStatement preStmt = conn
						.prepareStatement("INSERT INTO pizzas (type, slices, stuffedCrust) VALUES(?, ?, ?)");
				preStmt.setString(1, pizza.get(i).getType());
				preStmt.setInt(2, pizza.get(i).getSlices());
				preStmt.setBoolean(3, pizza.get(i).isStuffedCrust());
				preStmt.executeUpdate();
				System.out.println(pizza.get(i).getType() + " pizza added to database");
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}

	// Creating a view all query to send to the database
	// Implementing a way to read the data JDBC sends us

	public Pizza viewPizza(long id) {
		try {
			conn = jdbc.connect();
			stmt = conn.createStatement();
			String query = "SELECT * FROM pizzas WHERE id = " + id;
			ResultSet result = stmt.executeQuery(query);
			// Our result is a spreadsheet from the database, with the first row being meta
			// data
			// Go down to the next row of data (Our useful data)
			result.next();
			// Pass in the useful data to our returnResults method, return this
			return jdbc.returnResults(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void viewAllPizzas() {
		try {
			conn = jdbc.connect();
			stmt = conn.createStatement();
			String query = "SELECT * FROM pizzas";
			// result - All objects from our database
			ResultSet result = stmt.executeQuery(query);
			// While result.next() = true {do this thing}
			// result ISNT an array
			// NOT SAYING Loop through the array and print out each document
			// result = a spreadsheet, change the row we're focusing on
			while (result.next()) {
				System.out.println(jdbc.returnResults(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Query - Reading data
	// Update - Modifying data (Read, delete, create)

	// Prepared Statements - nicer ways of making longer queries
	public void deletePizza(long id) {
		try {
			conn = jdbc.connect();
			// Prepared statements use SQL ? Syntax
			// ? relates to a variable we can pass in
			PreparedStatement preStmt = conn.prepareStatement("DELETE FROM pizzas where id = ?");
			// Find the first ? set its value to be a Long with a value of <id>
//			preStmt.setString(1, "id");
			preStmt.setLong(1, id);
			// Execute the statement
			preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePizzaSlice(int slices) {
		try {
			conn = jdbc.connect();
			PreparedStatement preStmt = conn.prepareStatement("DELETE FROM pizzas where slices = ?");
			preStmt.setLong(1, slices);
			preStmt.executeUpdate();
			System.out.println("Removed all pizzas with " + slices + "slices");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePizzaSC(boolean stuffedCrust) {
		try {
			conn = jdbc.connect();
			PreparedStatement preStmt = conn.prepareStatement("DELETE FROM pizzas where stuffedCrust = ?");
			preStmt.setBoolean(1, stuffedCrust);
			preStmt.executeUpdate();
			if (stuffedCrust == true) {
				System.out.println("Removed all pizzas with stuffed crust");
			} else {
				System.out.println("Removed all pizzas without stuffed crust");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//
//	public void deleteMultiple(List<Pizza> pizza) {
//		for (int i = 0; i < pizza.size(); i++) {
//			try {
//				conn = jdbc.connect();
//				PreparedStatement preStmt = conn
//						.prepareStatement("INSERT INTO pizzas (type, slices, stuffedCrust) VALUES(?, ?, ?)");
//				preStmt.setString(1, pizza.get(i).getType());
//				preStmt.setInt(2, pizza.get(i).getSlices());
//				preStmt.setBoolean(3, pizza.get(i).isStuffedCrust());
//				preStmt.executeUpdate();
//				System.out.println(pizza.get(i).getType() + " pizza added to database");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
