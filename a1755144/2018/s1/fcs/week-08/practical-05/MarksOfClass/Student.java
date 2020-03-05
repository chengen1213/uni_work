import java.util.ArrayList;

public class Student {
    //attributes
    private String name;
    private int physic;
    private int chemistry;
    private int math;
    private double average;
    //empty constructor
    public Student() {
        super();
    }
    //constructor with attributes
    public Student(String name, int physic, int chemistry, int math) {
        this.name = name;
        this.physic = physic;
        this.chemistry = chemistry;
        this.math = math;
        this.average = ((double) physic + chemistry + math) / 3;
    }
    //accessors and mutators
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhysic() {
        return physic;
    }

    public void setPhysic(int physic) {
        this.physic = physic;
    }

    public int getChemistry() {
        return chemistry;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Student[] sortStudents(Student[] toBeSorted) {
        Student[] array = toBeSorted.clone();
        return quickSort(array, 0, array.length - 1);
    }

    //use quick sort algorithm to sort students
    private Student[] quickSort(Student[] array, int index1, int index2) {

        if (index1 < index2) {
            int pivotPos = divide(array, index1, index2);
            quickSort(array, index1, pivotPos - 1);
            quickSort(array, pivotPos + 1, index2);
        }
        return array;
    }

    private int divide(Student[] array, int index1, int index2) {

        Student pivot = array[index2];
        int pivotPos = index1;

        for (int i = index1; i < index2; i++) {
            //studentComparator define how to compare students
            if (array[i].studentComparator(pivot)) {
                swap(array, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, pivotPos, index2);
        return pivotPos;
    }

    private void swap(Student[] array, int index1, int index2) {

        Student temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private boolean studentComparator(Student student) {

        double avgDif = this.getAverage() - student.getAverage();
        double phsDif = this.getPhysic() - student.getPhysic();
        double chmDif = this.getChemistry() - student.getChemistry();
        double mathDif = this.getMath() - student.getMath();
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(avgDif);
        arrayList.add(phsDif);
        arrayList.add(chmDif);
        arrayList.add(mathDif);
        for (Double value : arrayList) {
            if (value != 0) {//same grade, compare the next subject
                if (value > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        //all subjects have the same grades, compare name in ascending order
        return !stringComparator(this.getName(), student.getName());
    }
    //stringComparator define how to compare Strings
    private boolean stringComparator(String string1, String string2) {
        int length;
        boolean b;
        if (string1.length() > string2.length()) {
            length = string2.length();
            b = true;
        } else {
            length = string1.length();
            b = false;
        }
        //compare each character
        for (int i = 0; i < length; i++) {
            if (string1.charAt(i) > string2.charAt(i)) {
                return true;
            } else if (string1.charAt(i) < string2.charAt(i)) {
                return false;
            }
        }
        //if all characters are the same, compare length
        return b;
    }

}