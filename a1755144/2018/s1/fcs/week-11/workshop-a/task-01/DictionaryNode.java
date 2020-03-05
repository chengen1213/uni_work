import java.util.HashMap;
import java.util.LinkedList;

public class DictionaryNode implements Comparable {

    private String alphabet;
    private BinaryTree binaryTree;
    private HashMap<String, DictionaryNode> hashMap;

    DictionaryNode(String value) {
        alphabet = value;
        binaryTree = new BinaryTree();
        hashMap = new HashMap<>();
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public BinaryTree getBinaryTree() {
        return binaryTree;
    }

    public void setBinaryTree(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    public HashMap getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public int compareTo(Object o) {
        return alphabet.compareTo(((DictionaryNode) o).getAlphabet());
    }

    public void print(DictionaryNode dn) {
        System.out.print(dn.getAlphabet());
        HashMap<String, DictionaryNode> subNodes = dn.getHashMap();
        for(String s : subNodes.keySet()){
            print(subNodes.get(s));
        }
    }
}