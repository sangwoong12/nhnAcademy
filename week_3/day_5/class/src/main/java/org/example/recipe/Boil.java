package org.example.recipe;

import org.example.Acceptor;

public class Boil implements Visitor{

	@Override
	public void visit(Acceptor acceptor) {
		System.out.println("Starting art ....");
		System.out.println("물을 끓입니다.");
		System.out.println(acceptor.getName() + " " + acceptor.getQuantity()
			+ acceptor.getUnit() + "을 물에 삶습니다.");
		System.out.println("건져냅니다.");
		System.out.println("Ending art ....");
	}
}
