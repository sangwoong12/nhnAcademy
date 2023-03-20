package org.example.visitor;

import org.example.color.BlueToken;
import org.example.color.OrangeToken;
import org.example.color.YellowToken;

public interface Visitor {

	String visit(BlueToken color);
	String visit(OrangeToken color);
	String visit(YellowToken color);
	String visit(String base);
}
