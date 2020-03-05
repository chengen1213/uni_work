public class Fibonacci{
	public int fibonacci(int index){
		if(index<=0){
			return -1;
		}
		if(index == 1||index ==2){
			return 1;
		}
		return fibonacci(index-1)+fibonacci(index-2); 
	}
}