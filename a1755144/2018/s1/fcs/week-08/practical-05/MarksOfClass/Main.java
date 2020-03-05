import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Student student = new Student();
        Student[] students = student.sortStudents(readData("students.txt"));
        printStudents(students);

    }

    private static Student[] readData(String filename) {

        ArrayList<Student> arrayList = new ArrayList<>();
        try {
            //load file
            BufferedReader br = new BufferedReader(new FileReader(filename));
            //read next line of the file
            String line = br.readLine();
            //keep reading until reach the end of the file
            while (line != null) {
                String[] content = line.split(" ");
                if (content.length == 4) {
                    //instantiate the student object with the information in the file
                    Student student = new Student(content[0], Integer.parseInt(content[1]),
                            Integer.parseInt(content[2]), Integer.parseInt(content[3]));
                    arrayList.add(student);
                }
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return arrayList.toArray(new Student[arrayList.size()]);
        }
    }

    private static void printStudents(Student[] students) {
        int avgLength = 25;
        int length = 15;
        //print tittle
        System.out.println(spacing("Student", length) + spacing("AverageScore", avgLength) 
            + spacing("Physic", length) + spacing("Chemistry", length) + spacing("Maths", length));
        //print information of each student, using spacing method to control the width of the elements
        for (Student student : students) {
            System.out.println(spacing(student.getName(), length) 
                + spacing(new Double(student.getAverage()).toString(), avgLength)
                + spacing(new Integer(student.getPhysic()).toString(), length) 
                + spacing(new Integer(student.getChemistry()).toString(), length) 
                + spacing(new Integer(student.getMath()).toString(), length));
        }
    }

    //set the width of the context
    private static String spacing(String target, int length) {

        int spaceNum = target.length();
        for (int i = 0; i < length - spaceNum; i++) {
            target += " ";
        }
        return target;
    }

//    public static Student[] sortStudents(Student[] students) {
//        ArrayList<Student> studentList = new ArrayList<>();
//        for (Student student : students) {
//            studentList.add(student);
//        }
//        studentList.sort(new Comparator<Student>() {
//            @Override
//            public int compare(Student student, Student t1) {
//                double avgDif = t1.getAverage() - student.getAverage();
//                double phsDif = t1.getPhysic() - student.getPhysic();
//                double chmDif = t1.getChemistry() - student.getChemistry();
//                double mathDif = t1.getMath() - student.getMath();
//                ArrayList<Double> arrayList = new ArrayList();
//                arrayList.add(avgDif);
//                arrayList.add(phsDif);
//                arrayList.add(chmDif);
//                arrayList.add(mathDif);
//                for (Double value : arrayList) {
//                    if (value != 0) {
//                        if (value > 0) {
//                            return 1;
//                        } else if (value == 0) {
//                            return 0;
//                        } else {
//                            return -1;
//                        }
//                    }
//                }
//                return 0;
//            }
//        });
//
//        for (int i = 0; i < students.length; i++) {
//            students[i] = studentList.get(i);
//        }
//
//        return students;
//    }

}