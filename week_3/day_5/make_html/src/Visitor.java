public interface Visitor {

	String visit(BlueToken color);
	String visit(OrangeToken color);
	String visit(YellowToken color);
	String visit(String base);
}
