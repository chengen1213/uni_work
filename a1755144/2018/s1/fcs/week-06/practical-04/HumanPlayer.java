public class HumanPlayer extends Player {

    //constructor inherit from super class
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public String performMove() {
        boolean redo = true;
        String playerMove = "";

        //replay loop
        while (redo) {
            System.out.print("Rock, paper or scissors? ");
            playerMove = Interpreter.check(System.console().readLine());//check input
            if (!playerMove.equals("error")) {
                redo = false;//loop controller
            }
            if (redo) {
                System.out.println("Not a valid input.");
            }
        }

        return playerMove;
    }
}
