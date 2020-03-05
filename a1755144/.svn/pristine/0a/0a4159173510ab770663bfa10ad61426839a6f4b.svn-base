import java.util.ArrayList;
import java.util.HashMap;

public class TreeNode<T> {
    int level;
    String classifyBy;
    ArrayList<String> attributes;
    HashMap<Integer, TreeNode> subNodes;
    ArrayList<T> subData;

    public TreeNode(int level, ArrayList<String> attributes, ArrayList<T> subIris) {
        super();
        this.level = level;
        this.classifyBy = "";
        this.attributes = attributes;
        this.subNodes = new HashMap<>();
        this.subData = subIris;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getClassifyBy() {
        return classifyBy;
    }

    public void setClassifyBy(String classifyBy) {
        this.classifyBy = classifyBy;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    public HashMap<Integer, TreeNode> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(HashMap<Integer, TreeNode> subNodes) {
        this.subNodes = subNodes;
    }

    public ArrayList<T> getSubData() {
        return subData;
    }

    public void setSubData(ArrayList<T> subData) {
        this.subData = subData;
    }
}
