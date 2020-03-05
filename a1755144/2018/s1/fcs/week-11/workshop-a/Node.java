public class Node {

    private String value;
    private Node left;
    private Node right;

    public Node() {
        value = "";
        left = null;
        right = null;
    }

    public Node(String value) {
        this.value = value;
        left = null;
        right = null;
    }

    public void print() {
        System.out.print(value + " ");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}