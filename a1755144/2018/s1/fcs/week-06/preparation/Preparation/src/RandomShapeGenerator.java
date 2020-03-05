/**
 * Created by a1755144 on 24/04/18.
 */
public class RandomShapeGenerator {
    public Shape next() {
        Shape shape;
        int shapeNum = (int) Math.random() * 3;
        if (shapeNum == 0) {
            shape = new Circle();
        } else if (shapeNum == 1) {
            shape = new Square();
        } else {
            shape = new Triangle();

        }
        return shape;
    }
}
