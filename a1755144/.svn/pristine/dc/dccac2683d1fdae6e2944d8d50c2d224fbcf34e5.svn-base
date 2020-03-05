
public class Main {

    public static void main(String[] args) {

        boolean redo = true;
        String playAgain = "";//receive user input
        Referee referee = new Referee();
        Player player1 = new HumanPlayer("Player");
        Player player2 = new ComputerPlayer("Computer");
        referee.getPlayer(player1, player2);
        while (redo) {//play again?
            System.out.println("Start!");
            referee.askForInput();
            referee.result();
            System.out.println("Play again? : 'C' to continue, any key else to leave.");
            playAgain = System.console().readLine();//read input
            if (!playAgain.toLowerCase().equals("c")) {
                redo = false;
            }
        }
    }
}
/* The main method use a while-loop to let user play the game repeatedly.
To continue the process, it will instantiate a Referee and then instantiate
a HumanPlayer and a ComputerPlayer with the Type of Player.
Since both of the two class are subclass of abstract class Player, the object of Referee
can call the getPlayer method with this two objects to initialize the Player attributes.
Then the instance of the Referee call the askForInput method to perform move and result method to show result.
In the HumanPlayer object, it perform a move by getting the input from user, on the other hand,
the ComputerPlayer will generate a random number and translate it to string type.*/ 