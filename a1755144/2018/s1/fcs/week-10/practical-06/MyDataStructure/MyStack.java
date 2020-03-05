public class MyStack implements Stack {

    private int maxSize; // the maximum size of your stack
    private String[] stackArray; // an array to save all elements in your stack
    private int top; // the index of top element in your stack

    //constructor:
    public MyStack() {

        maxSize = 100;
        stackArray = new String[maxSize];
        top = -1;
    }

    public MyStack(int size) {

        maxSize = size;
        stackArray = new String[maxSize];
        top = -1;
    }

    //Methods:
    public void push(String s) {

        if (isFull()) {
            System.out.println("The stack is full!");
        } else {
            //move the cursor and store the element
            stackArray[++top] = s;
        }
    }

    public String pop() {

        String element = null;
        if (isEmpty()) {
            System.out.println("The stack is empty!");
        } else {
            //save the element to be pop and move the cursor
            element = stackArray[top--];
        }
        return element;
    }

    public boolean isEmpty() {

        return top == -1;
    }

    public boolean isFull() {

        return top == maxSize - 1;
    }

    //true:s is in the stack, false: not in the stack
    //if s exist in the stack, remove it
    public boolean check(String s) {

        for (int i = 0; i <= top; i++) {
            if (stackArray[i].equals(s)) {
                shiftArray(i);
                return false;
            }
        }
        return true;
    }

    //remove the element at the given position
    private void shiftArray(int index) {

        for (int i = index; i < top; i++) {
            stackArray[i] = stackArray[i + 1];
        }
        top--;
    }
}