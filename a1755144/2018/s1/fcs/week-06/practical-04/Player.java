public abstract class Player {

    protected String name;

    public Player() {
        this.name = "default";
    }

    public Player(String name) {
        this.name = name;
    }

    //setter
    public void setName() {
        this.name = name;
    }

    //getter
    public String getName() {
        return name;
    }

    //abstract method to be implemented
    public abstract String performMove();
}
