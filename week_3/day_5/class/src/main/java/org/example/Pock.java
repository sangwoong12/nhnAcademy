package org.example;

import org.example.recipe.Visitor;

public class Pock extends Acceptor{

	public Pock(String name, int quantity, String unit) {
		super(name, quantity, unit);
	}
	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
