public class TestArray {

    public static void printArray(double [] testArray) {
     // your code goes here
    	String output = "Printing Array: [";
    	for(int i = 0; i<testArray.length; i++){
    		if(i < testArray.length-1){
    			output = output + testArray[i] + ","; 
    		}else{
    			output = output + testArray[i] + "]";
    		}
    	}
    	System.out.println(output);
     }

    public static double[] sumElements(double [] firstArray, double [] secondArray) {
     // your code goes here
    	double[] sumAry = new double[firstArray.length];
    	if(firstArray.length != secondArray.length){//compare the length of the 2 arrays
    		System.out.println("Error - Arrays different shape.");
    	}else{
    		for(int i = 0; i<firstArray.length; i++){
    			sumAry[i] = firstArray[i] + secondArray[i];
    		}
    	}
    	return sumAry;
     }
}