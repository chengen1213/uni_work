public class ComputerPlayer extends Player {

    //constructor inherit from super class
    public ComputerPlayer(String name) {
        super(name);
    }

    //generate random number(0~2) 
    @Override
    public String performMove() {
        return Interpreter.intToStr((int) (Math.random() * Interpreter.inforMap.length));
    }
}
