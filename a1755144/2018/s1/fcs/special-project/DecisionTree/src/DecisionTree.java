import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTree<T> {
    TreeNode treeNode;

    public TreeNode train(ArrayList<T> data, String type) {
        Class c = treeNode.getClass();
        Field[] fields = c.getDeclaredFields();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].getName().equals(type))
                hashMap.put(i, fields[i].getName());
        }
        treeNode = new TreeNode(0, hashMap, data);
        return treeNode;
    }

    public void findBestGain() {

    }

    public void purity() {

    }

    public void gain() {

    }
//    public void gainRatio(){
//
//    }
}
