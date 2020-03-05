import java.util.HashMap;
import java.util.LinkedList;

public class Dictionary {

    private DictionaryNode dictionaryRoot;

    public Dictionary() {
        dictionaryRoot = new DictionaryNode("");
    }

    public void addWord(String word) {
        dictionaryRoot = insert(dictionaryRoot, word);
    }

    public DictionaryNode insert(DictionaryNode node, String word) {
        if (word.length() > 0) {
            String value = new StringBuffer().append(word.charAt(0)).toString();

//            BinaryTree binaryTree = node.getBinaryTree();
            HashMap<String, DictionaryNode> subNodes = node.getHashMap();
//            Node toAdd = new Node(new DictionaryNode(value));
//            if (!binaryTree.find(toAdd)) {
//                binaryTree.insert(toAdd);
//            }
            if(subNodes.get(value)==null){
                subNodes.put(value, new DictionaryNode(value));
            }
            if (word.length() > 1) {
                String remains = word.substring(1);
//                insert((DictionaryNode) binaryTree.get(toAdd).getValue(), remains);
                insert(subNodes.get(value), remains);

            }
        }
        return node;
    }

    public void print() {
        this.dictionaryRoot.print(dictionaryRoot);
    }
}