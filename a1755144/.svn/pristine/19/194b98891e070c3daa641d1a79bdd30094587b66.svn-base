public class BinaryTree {

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void insertTree(Node node){
        this.root = insert(root,node);
    }

    public Node insert(Node root, Node node) {
        if (root == null) {
            root = node;
        } else {
            if (node.getValue().compareTo(root.getValue()) > 0) {
                root.setRight(insert(root.getRight(), node));
            } else {
                root.setLeft(insert(root.getLeft(), node));
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

    public boolean find(Node root, Node node) {
        if (root == null) {
            return false;
        } else {
            if (node.getValue().compareTo(root.getValue()) > 0) {
                return find(root.getRight(), node);
            } else if (node.getValue().compareTo(root.getValue()) < 0) {
                return find(root.getLeft(), node);
            } else {
                return true;
            }
        }
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