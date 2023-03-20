import org.example.Pock;
import org.example.recipe.Boil;
import org.example.recipe.Fry;
import org.example.recipe.Visitor;

public class Test {

	public static void main(String[] args) {
		Pock pock = new Pock("돼지고기.",200,"g");

		Visitor visitor = new Boil();
		Visitor visitor1 = new Fry();
		pock.accept(visitor1);
	}

}
