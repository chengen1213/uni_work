public class Main {

    public static void main(String[] args) {

        String[] values = {"d","b","a","c","f","e","g"};
        BinaryTree bt = new BinaryTree();
        Node root = bt.getRoot();
        for (String value : values) {
            root = bt.insert(root, new Node(value));
        }
        bt.printTree(root);
        System.out.print(bt.find(root, new Node("d")));

//        String[] values2 = {"b","a","c","f","e","g"};
//        BinaryTree bt2 = new BinaryTree(new Node("d"));
//        Node root2 = bt2.getRoot();
//        for (String value : values2) {
//            bt2.insert2(root2, new Node(value));
//        }
//        bt2.printTree(root2);
//        System.out.print(bt2.find(root2, new Node("d")));
    }
}
