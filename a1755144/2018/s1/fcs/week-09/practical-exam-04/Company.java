/*
*   Foundations of Computer Science
*   2018, Semester 01
*   Practical-Exam-04
*
*   student (id): a111111
*   student (name): John Smitth
*
* Note: in order to finish your exam you need to make changes in this class
* Note: your are also required to perform small changes, as accessors and mutators;

*
*/
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Company{
	private int maxEmp;
	private int curStaff;
	private Employee [] staff;
	private Sort sorting;
	private Search searching;
	private boolean toLoad;

	Company(){
		this.staff = new Employee [100];
		this.maxEmp = this.staff.length;
		this.curStaff = 0;
		// this.sorting = new Insertion();
		this.sorting = new Selection();
		this.searching = new BinarySearch();
		//this.searching = new LinearSearch();
		this.toLoad = true;

	}

	public void sortStaffByName(){
		String [] names = new String[this.curStaff];
		for(int i=0; i < this.curStaff; i++){
			names[i] = this.staff[i].getName();
			names[i] = names[i].toLowerCase();
		}
		int [] sorted = sorting.sortStringByIndex(names);
		Employee [] tmp = new Employee[this.maxEmp];
		for(int j=0; j < this.curStaff; j++){
			tmp[j] = this.staff[sorted[j]];
		}
		this.staff = tmp;
	}

	public void searchStaffByAge(int query){
		int [] ages = new int[this.curStaff];
		for(int i=0; i < this.curStaff; i++){
			ages[i] = this.staff[i].getAge();
		}
		// //using binary search, we need to sort target array first
		// int [] sorted = sorting.sortIntByIndex(ages);
		// //create a new Employee array to save the sorted elements
		// Employee [] tmp = new Employee[this.maxEmp];
		// for(int j=0; j < this.curStaff; j++){
		// 	//load data from the sorted index
		// 	tmp[j] = this.staff[sorted[j]];
		// }
		// //save the sorted result to the attribute
		// this.staff = tmp;
		sortStaffByAge();
		int staffIndex = searching.search(ages, query);
		if(staffIndex != -1)
			this.staff[staffIndex].display();
		else
			System.out.println(":: Staff does not exist, try again!");	
	}

	public void searchStaffByName(String query){
		String [] names = new String[this.curStaff];
		for(int i=0; i < this.curStaff; i++){
			names[i] = this.staff[i].getName();
			names[i] = names[i].toLowerCase();
		}
		// //using binary search, we need to sort target array first
		// int [] sorted = sorting.sortStringByIndex(names);
		// //create a new Employee array to save the sorted elements
		// Employee [] tmp = new Employee[this.maxEmp];
		// for(int j=0; j < this.curStaff; j++){
		// 	//load data from the sorted index
		// 	tmp[j] = this.staff[sorted[j]];
		// }
		// //save the sorted result to the attribute
		// this.staff = tmp;
		sortStaffByName();
		int staffIndex = searching.search(names, query.toLowerCase());
		if(staffIndex != -1)
			this.staff[staffIndex].display();
		else
			System.out.println(":: Staff does not exist, try again!");	
	}

	public void sortStaffByAge(){
		int [] ages = new int[this.curStaff];
		for(int i=0; i < this.curStaff; i++){
			ages[i] = this.staff[i].getAge();
		}
		int [] sorted = sorting.sortIntByIndex(ages);//, true);
		Employee [] tmp = new Employee[this.maxEmp];
		for(int j=0; j < this.curStaff; j++){
			tmp[j] = this.staff[sorted[j]];
		}
		this.staff = tmp;
	}


	public void displayStaff(){
		String header = String.format(":: %s %25s %8s %8s %8s", "Name", "Age", "(hpw)", "(dph)", "(wyears)");
		header += "\n";
		header += "---------------------------------------------------------------";

		System.out.println(":: Display staff members");
		System.out.println(":: Total staff: " + this.curStaff);
		System.out.println(header);
		for(int i = 0; i < this.curStaff; i++){
			staff[i].display();
		}
		header = "";
		if(this.toLoad)
			header += ":: No staff loaded yet!" + "\n";
		header += "---------------------------------------------------------------";
		System.out.println(header);
	}

	public void setStaff(String [] data){
		if(data.length != 5)
			throw new java.lang.Error("::setStaff: incorrect number of args");
			String name = data[0];
			int age = Integer.parseInt(data[1]);
			float dollarsPerHours = Float.parseFloat(data[2]);
			int hoursPerWeek = Integer.parseInt(data[3]);
			int workedYears = Integer.parseInt(data[4]);
			this.staff[this.curStaff] = new Employee(name, age, hoursPerWeek, dollarsPerHours, workedYears);
			this.curStaff++;
	}

	public void loadStaff(String filePath){
		if(this.toLoad){
			System.out.println(":: Loading staff members...");
			try{
				File file = new File(filePath);
		        Scanner inputStream = new Scanner(file);
				inputStream.next();
		        while(inputStream.hasNext()){
	            	String data = inputStream.next();
	            	String [] sData = data.split(",");
	            	this.setStaff(sData);
	        	}
				System.out.println(":: " + this.curStaff + " staff members loaded!");
		    }catch (FileNotFoundException e){
	            e.printStackTrace();
	        }
	        this.toLoad = false;
		} else{
			System.out.println(":: Staff members were already loaded!");
			System.out.println(this.curStaff + " staff members loaded!");
		}
	}

}