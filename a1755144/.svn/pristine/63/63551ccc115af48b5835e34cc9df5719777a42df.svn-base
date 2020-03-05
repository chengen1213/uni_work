public class Test {
    public static void main(String[] args) {
        int[] array = {9, 5, 7, 8, 1, 2, 4, 3, 6};
        QuickSort qs = new QuickSort();
        qs.sort(array);
        for (int i : array) {
            System.out.print(i + ",");
        }
        System.out.println();
        String[] array2 = {"a","b","ab","abc","ac"};
        for (int i : qs.sort(array2)) {
            System.out.print(i + ",");
        }
        System.out.println();

        School school = new School();

        for (Student s : school.students) {
            System.out.print(s.getName() + ",");
        }
        System.out.println();
        Student[] students = school.sortStudentsByName(school.students);
        for (Student s : students) {
            System.out.print(s.getName() + ",");
        }

    }
}