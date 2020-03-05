public class BinarySearch{

	public int search(int[] array, int target){
		int left = 0;
		int right = array.length-1;
		while (right>=left) {
			int mid = (left+right)/2;
			if(target>array[mid]){
				left = mid+1;
			}else if (target<array[mid]) {
				right = mid-1;
			}else {
				return  mid;
			}
		}
		return -1;
	}

	public int binarySearch(int[] array, int target){
		return search(array,0,array.length-1,target);
	}

	public int search(int[] array, int left, int right, int target){
		if(right>=left){
			int mid = (left+right)/2;
			if(target>array[mid]){
				return search(array,mid+1,right,target);
			}else if (target<array[mid]) {
				return search(array,left,mid-1,target);
			}else {
				return mid;
			}		
		}
		return -1;
	}
}