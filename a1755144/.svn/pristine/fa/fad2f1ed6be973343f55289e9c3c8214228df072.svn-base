
public class Referee {

    private Player player1;
    private Player player2;
    private String player1Choose;
    private String player2Choose;


    public void getPlayer(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void askForInput() {
        player1Choose = player1.performMove();
        player2Choose = player2.performMove();
    }

    public void result() {

        //define variables
        String result = "";
        int playerNum = Interpreter.strToInt(player1Choose);
        int comNum = Interpreter.strToInt(player2Choose);

        //compare choice
        if (playerNum == 0 && comNum == 2) {
            result = player1.getName() + " win!";
        } else if (playerNum == 2 && comNum == 0) {
            result = player2.getName() + " win!";
        } else if (playerNum > comNum) {
            result = player1.getName() + " win!";
        } else if (playerNum == comNum) {
            result = "Draw!";
        } else {
            result = player2.getName() + " win!";
        }

        //print result
        System.out.println(player1.getName() + " choose " + player1Choose + "!");
        System.out.println(player2.getName() + " choose " + player2Choose + "!");
        System.out.println(result);
    }
}
