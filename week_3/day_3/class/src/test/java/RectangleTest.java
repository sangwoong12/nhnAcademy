import org.example.AreaCalculator;
import org.example.lsp.Rectangle;
import org.example.lsp.Square;

public class RectangleTest {

    public static void main(String[] args) {
        AreaCalculator calc = new AreaCalculator();
        Number n = calc.sumOfShapes(new Rectangle(10,5),new Square(10));
        System.out.println(n.toString());

    }
}
