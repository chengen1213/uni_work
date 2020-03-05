public class MyQueue {
    //Attributes:
    private int front, rear, size; // the index for firs, the index for last array and current size of your queue
    private String[] queueArray; // an array to save all elements in your queue
    private int maxSize; // the maximum size of your queue

    //constructor:
    public MyQueue() {

        maxSize = 100;
        queueArray = new String[maxSize];
        front = 0;
        rear = maxSize - 1;
        size = 0;
    }

    public MyQueue(int size) {

        maxSize = size;
        queueArray = new String[maxSize];
        front = 0;
        rear = maxSize - 1;
        this.size = 0;
    }

    //Methods:
    public void enqueue(String s) {
        if (isFull()) {
            System.out.println("The queue is full!");
        } else {
            //move the rear cursor and store the element
            queueArray[++rear % maxSize] = s;
            size++;
        }
    }

    public String dequeue() {
        String tmp = null;
        if (isEmpty()) {
            System.out.println("The queue is empty!");
        } else {
            //prepare the element and move the front cursor
            tmp = queueArray[front];
            front = ++front % maxSize;
            size--;
        }
        return tmp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public int getSize() {
        return size;
    }

    // public boolean ckeck(String s){
    // 	if(isEmpty()){
    // 		return true;
    // 	}
    // 	int endIndex = rear;
    // 	if(rear < front){
    // 		endIndex = rear + maxSize;
    // 	}
    // 	for(int i = front; i <= endIndex; i++){
    // 		int index =  i%maxSize;
    // 		if(queueArray[index].equals(s)){
    // 			shiftArray(index);
    // 		}
    // 		return false;
    // 	}
    // 	return true;
    // }

    // private void shiftArray(int index){
    // 	int endIndex = rear;
    // 	if(rear < index){
    // 		endIndex = rear + maxSize;
    // 	}
    // 	for(int i = index; i < endIndex; i++){
    // 		int curPos = i%maxSize;
    // 		queueArray[curPos] = queueArray[(curPos+1)%maxSize];
    // 	}
    // 	if(rear>0){
    // 		rear--;
    // 	}else{
    // 		rear = rear+100-1;
    // 	}
    // }
}