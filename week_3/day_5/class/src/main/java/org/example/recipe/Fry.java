package org.example.recipe;

import org.example.Acceptor;

public class Fry implements Visitor{

	@Override
	public void visit(Acceptor acceptor) {
		System.out.println("Starting art ....");
		System.out.println("기름을 끓입니다.");
		System.out.println(acceptor.getName() + " " + acceptor.getQuantity()
			+ acceptor.getUnit() + "을 기름에 튀깁니다.");
		System.out.println("건져냅니다.");
		System.out.println("Ending art ....");
	}
}
