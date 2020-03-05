/**
 * Created by a1755144 on 24/04/18.
 */
public class Main {
    public static void main(String[] args){
        Shape[] array = new Shape[10];
        RandomShapeGenerator randomShapeGenerator = new RandomShapeGenerator();
        for(int i = 0; i < array.length; i++){
            array[i] = randomShapeGenerator.next();
            array[i].drawShape();
        }
    }
}
