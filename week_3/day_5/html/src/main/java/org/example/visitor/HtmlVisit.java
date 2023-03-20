package org.example.visitor;

import org.example.color.BlueToken;
import org.example.color.OrangeToken;
import org.example.color.YellowToken;

public class HtmlVisit implements Visitor {

	@Override
	public String visit(BlueToken color) {
		return "<span class = \"colorToken\" style=\"color:#00a1ff\">" + color.getStr() + "</span>";
	}

	@Override
	public String visit(OrangeToken color) {
		return "<span class = \"colorToken\" style=\"color:orange\">" + color.getStr() + "</span>";
	}

	@Override
	public String visit(YellowToken color) {
		return "<span class = \"colorToken\" style=\"color:yellow\">" + color.getStr() + "</span>";
	}

	@Override
	public String visit(String base) {
		return base;
	}
}
