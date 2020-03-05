import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        ArrayList<Stack> stacks = new ArrayList<>();
        MyStack myStack = new MyStack(5);
        StackByQueue stackByQueue = new StackByQueue(5);
        stacks.add(myStack);
        stacks.add(stackByQueue);
        //test for MyStack & StackByQueue
        for (Stack stack : stacks) {

            String stackName = stack.getClass().getName();
            System.out.println();
            System.out.println("Test for " + stackName + " ( size = 5 ):");
            System.out.println();
            //fill the stack
            while (!stack.isFull()) {
                String temp = getRandomString();
                System.out.println("Push string " + temp + " into " + stackName + ".");
                stack.push(temp);
            }

            System.out.println();
            System.out.println(stackName + " is full? : " + stack.isFull());
            System.out.println();
            //test push when the stack is full
            String temp = getRandomString();
            System.out.println("Push string " + temp + " into " + stackName + ".");
            stack.push(temp);

            System.out.println();
            while (!stack.isEmpty()) {
                System.out.println("Pop string " + stack.pop() + " from " + stackName + ".");
            }
            System.out.println();
            System.out.println(stackName + " is empty? : " + stack.isEmpty());
            System.out.println();
            //test pop when the stack is empty
            System.out.println("Pop string " + stack.pop() + " from " + stackName + ".");
        }


        System.out.println();
        System.out.println("Test for MyQueue ( size = 5 ):");
        System.out.println();
        //fill the queue
        MyQueue queue = new MyQueue(5);
        while (!queue.isFull()) {
            String temp = getRandomString();
            System.out.println("Enqueue string " + temp + " into MyQueue.");
            queue.enqueue(temp);
        }

        System.out.println();
        System.out.println("MyQueue is full? : " + queue.isFull());
        System.out.println();
        //test enqueue when the queue is full
        String temp = getRandomString();
        System.out.println("Enqueue string " + temp + " into MyQueue.");
        queue.enqueue(temp);

        System.out.println();
        System.out.println("Test for reverseQueue:");
        System.out.println();
        System.out.println("Reverse MyQueue!!!");
        System.out.println();
        //test reverseQueue method
        reverseQueue(queue);

        while (!queue.isEmpty()) {
            System.out.println("Dequeue string " + queue.dequeue() + " from MyQueue.");
        }

        System.out.println();
        System.out.println("MyQueue is empty? : " + queue.isEmpty());
        //test dequeue when the queue is empty
        System.out.println();
        System.out.println("Dequeue string " + queue.dequeue() + " from MyQueue.");

        System.out.println();
        System.out.println("Test for removeRepeatedLetters:");
        System.out.println();
        //test removeRepeatedLetters method
        System.out.println("Input letters: abcdefgabcd, output = " + removeRepeatedLetters("abcdefgabcd") + ".");
        System.out.println("Input letters: aaaaabbbcaaaccd, output = " + removeRepeatedLetters("aaaaabbbcaaaccd") + ".");
        System.out.println("Input letters: aaaabbbcaaaccdEEE, output = " + removeRepeatedLetters("aaaabbbcaaaccdEEE") + ".");
        System.out.println();
    }

    private static String removeRepeatedLetters(String input) {

        MyStack stack = new MyStack(input.length());
        MyStack stack2 = new MyStack(input.length());
        String[] chars = input.split("");
        for (String s : chars) {
            //check if the char is in the stack
            if (stack.check(s)) {
                //doesn't exist, push it in
                stack.push(s);
            }
        }
        String result = "";
        //reverse the stack
        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        //concat the items in the queue and return
        while (!stack2.isEmpty()) {
            result += stack2.pop();
        }
        return result;
    }

    private static MyQueue reverseQueue(MyQueue queue) {
        //using stack to reverse the queue
        MyStack stack = new MyStack(queue.getSize());
        while (!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }
        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }
        return queue;
    }

    public static String getRandomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] alphabets = alphabet.split("");
        String result = "";
        Random rand = new Random();
        int lengthOfString = rand.nextInt(3);
        for (int i = 0; i < 5 + lengthOfString; i++) {
            result += alphabets[rand.nextInt(alphabets.length - 1)];
        }
        return result;
    }
}