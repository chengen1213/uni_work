public class LinearSearch {

	public int search(int[] array, int value){
		for(int i = 0; i<array.length;i++){
			if(array[i] == value){
				return i;
			}
		}
		return -1;
	}

	public int search(int[] array, int value, int index){

		if(index>=array.length){
			return -1;
		}

		if(array[index] == value){
			return index;
		}else{
			return search(array,value,index+1);
		}
	}

}