public class StackByQueue implements Stack {

    private MyQueue queue;
    private MyQueue tmpQueue;


    public StackByQueue() {
        queue = new MyQueue(100);
        tmpQueue = new MyQueue(100);
    }

    public StackByQueue(int size) {
        queue = new MyQueue(size);
        tmpQueue = new MyQueue(size);
    }

    public void push(String value) {
        if (queue.isFull()) {
            System.out.println("The stack is full!");
        } else {
            //move all the elements to the temporary queue
            while (!queue.isEmpty()) {
                tmpQueue.enqueue(queue.dequeue());
            }
            //save the new element in the target queue
            queue.enqueue(value);
            //move all elements back to target queue after the newest one
            while (!tmpQueue.isEmpty()) {
                queue.enqueue(tmpQueue.dequeue());
            }
        }

    }

    public String pop() {
        String output = "";
        if (queue.isEmpty()) {
            System.out.println("The stack is empty!");
        } else {
            output = queue.dequeue();
        }
        return output;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.isFull();
    }
}