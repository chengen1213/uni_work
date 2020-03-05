public class Main{
    public static void main(String[] args) {

        Boolean redo = true;
        int number = -1;
        Factorial factorial = new Factorial();
        while (redo) {
            System.out.println("Please enter a number");
            String input = System.console().readLine();
            try {
                number = Integer.parseInt(input);
                if (factorial.factorial(number) != -1) {
                    redo = false;
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
        System.out.println(factorial.factorial(number));

        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fibonacci(6));
    }
}