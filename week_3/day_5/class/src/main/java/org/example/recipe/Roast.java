package org.example.recipe;

import org.example.Acceptor;

public class Roast implements Visitor{

	@Override
	public void visit(Acceptor acceptor) {
		System.out.println("Starting art ....");
		System.out.println("불을 켭니다");
		System.out.println(acceptor.getName() + " " + acceptor.getQuantity()
			+ acceptor.getUnit() + "을 불에 굽습니다.");
		System.out.println("건져냅니다.");
		System.out.println("Ending art ....");
	}
}
