import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileInput {

    //	public static void main(String[] args) {
    @SuppressWarnings("finally")
    public static ArrayList<Iris> transferdata() {

        ArrayList<Iris> arrayList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/iris.txt"));

            String line = br.readLine();

            while (line != null) {
                String[] content = line.split(",");
                Iris iris = new Iris(Double.parseDouble(content[0]), Double.parseDouble(content[1]),
                        Double.parseDouble(content[2]), Double.parseDouble(content[3]), content[4]);
                arrayList.add(iris);
                line = br.readLine();
            }
//			for (Iris iris : arrayList) {
//				System.out.println("1:" + iris.getPetalLength() + " 2:" + iris.getPetalWidth() + " 3:"
//						+ iris.getSepalLength() + " 4:" + iris.getSepalWidth() + " 5:" + iris.getType());
//			}

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return arrayList;
        }
    }

}
