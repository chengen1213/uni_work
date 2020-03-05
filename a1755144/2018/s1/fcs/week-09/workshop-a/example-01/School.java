public class School{

	int numOfStudents;
	Student[] students;
	SortAlgs sortAlg;

	public School(){
		numOfStudents = 100;
		students = new Student[numOfStudents];
		sortAlg = new QuickSort();
	}

	public Student[] sortStudentsByName(Student[] students){
		String[] names = new String[students.length];
		for(int i = 0; i < students.length; i++){
			names[i] = students[i].getName();
		}
		int[] sortedIndices = sortAlg.sort(names);
		return arrangeStudentsByIndices(students, sortedIndices);
	} 

	public Student[] sortStudentsByAge(Student[] students){

	}

	public Student[] sortStudentsByGrade(Student[] students){

	} 

	public Student[] arrangeStudentsByIndices(Student[] students, int[] indices){
		Student[] sortedStudents = new Student[students.length];
		for(int i = 0; i < indices.length; i++){
			sortedStudents[i] = students[indices[i]];
		}
		return sortedStudents;
	}
}