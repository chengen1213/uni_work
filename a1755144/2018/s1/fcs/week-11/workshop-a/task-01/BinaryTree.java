public class BinaryTree {

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public  void insert(Node node){
        this.root = insertNode(this.root,node);
    }

    public Node insertNode(Node root, Node node) {
        if (root == null) {
            root = node;
        } else {
            if (node.getValue().compareTo(root.getValue()) > 0) {
                root.setRight(insertNode(root.getRight(), node));
            } else {
                root.setLeft(insertNode(root.getLeft(), node));
            }
        }
        return root;
    }

//    public void insert2(Node root, Node node) {
//        if (root == null) {
//            root = node;
//        } else {
//            if (node.getValue().compareTo(root.getValue()) > 0) {
//                insert2(root.getRight(), node);
//            } else {
//                insert2(root.getLeft(), node);
//            }
//        }
//    }

    public boolean find(Node node){
        return findNode(this.root,node);
    }

    public boolean findNode(Node root, Node node) {
        if (root == null) {
            return false;
        } else {
            if (node.getValue().compareTo(root.getValue()) > 0) {
                return findNode(root.getRight(), node);
            } else if (node.getValue().compareTo(root.getValue()) < 0) {
                return findNode(root.getLeft(), node);
            } else {
                return true;
            }
        }
    }

    public Node get(Node node){
        return getNode(this.root,node);
    }

    public Node getNode(Node root, Node node) {
        if (root == null) {
            return root;
        } else {
            if (node.getValue().compareTo(root.getValue()) > 0) {
                return getNode(root.getRight(), node);
            } else if (node.getValue().compareTo(root.getValue()) < 0) {
                return getNode(root.getLeft(), node);
            } else {
                return root;
            }
        }
    }

    public void printTree(Node node) {
        if (node == null) {
            return;
        } else {
            if (node.getLeft() != null) {
                printTree(node.getLeft());
            }
            node.print();
            if (node.getRight() != null) {
                printTree(node.getRight());
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}