public class BinarySearch {
	
	public int search(int[] array, int value){
		int leftIndex = 0;
		int rightIndex = array.length-1;
		while(rightIndex-leftIndex>=0){
			int midIndex = (rightIndex + leftIndex)/2;
			if(value>array[midIndex]){
				leftIndex = midIndex+1;
			}else if(value<array[midIndex]){
				rightIndex = midIndex-1;
			}else{
				return midIndex;
			}
		}
		return -1;
	}

	public int search(int[] array, int value, int leftIndex, int rightIndex){
		if(rightIndex-leftIndex<0){
			return -1;
		}
		int midIndex = (leftIndex+rightIndex)/2;
		if(array[midIndex] == value){
			return midIndex;
	    }else if(array[midIndex] > value){
	    	rightIndex = midIndex-1;
	    	return search(array,value,leftIndex,rightIndex);
	    }else{
	    	leftIndex = midIndex+1;
	    	return search(array,value,leftIndex,rightIndex);
	    }
	}	
}