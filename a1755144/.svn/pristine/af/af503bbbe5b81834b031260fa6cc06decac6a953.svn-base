
import java.util.Stack;

public class CalculatorImplementation implements Calculator{

    Stack<Integer> stack;

    public CalculatorImplementation() {
        stack = new Stack<>();
    }

    @Override
    public synchronized void pushValue(int operand) {
        this.stack.push(operand);
    }

    //according to different operator, perform different operation
    @Override
    public synchronized void pushOperator(String operator) {
        int rhs = stack.pop();
        int lhs = stack.pop();
        switch (operator) {
            case "+":
                stack.push(lhs + rhs);
                break;
            case "-":
                stack.push(lhs - rhs);
                break;
            case "*":
                stack.push(lhs * rhs);
                break;
            case "/":
                stack.push(lhs / rhs);
                break;
        }
    }

    @Override
    public synchronized int pop() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int delayPop(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            return stack.pop();
        }
    }
}
