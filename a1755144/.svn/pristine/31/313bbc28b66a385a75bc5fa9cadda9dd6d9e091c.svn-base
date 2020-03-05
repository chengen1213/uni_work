public class Main{
    public static void main(String[] args) {

        Boolean redo = true;
        int number = -1;
        Fibonacci fibonacci = new Fibonacci();
        while (redo) {
            System.out.println("Please enter a number");
            String input = System.console().readLine();
            try {
                number = Integer.parseInt(input);
                if (fibonacci.fibonacci(number) != -1) {
                    redo = false;
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
        System.out.println(fibonacci.fibonacci(number));
    }
}