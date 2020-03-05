public class BinaryTree{
	TreeNode root;
	public BinaryTree(){
		root = null;
	}

	public void add(int value){
		root = add(root,value);
	}

	public TreeNode add(TreeNode root, int value){
		TreeNode treeNode = new TreeNode(value);
		if(root==null){
			root = treeNode;
		}else {
			if(value > root.getValue()){
				root.setRight(add(root.getRight(),value));
			}else if (value < root.getValue()) {
				root.setLeft(add(root.getLeft(),value));
			}
		}
		return root;
	}

	public void addWhile(int value){
		TreeNode treeNode = new TreeNode(value);

		if(root!=null){
			// TreeNode prev = null;
			TreeNode current = root;
			while(current!=null){
				// prev = current;
				if(value > current.getValue()){
					if(current.getRight()!=null){
						current = current.getRight();
					}else {
						current.setRight(treeNode);
						current = null;
					}
				}else if (value < current.getValue()) {
					if(current.getLeft()!=null){
						current = current.getLeft();
					}else {
						current.setLeft(treeNode);
						current = null;
					}					
				}else {
					current = null;
				}
			}
			// if(value > prev.getValue()){
			// 	prev.setRight(treeNode);
			// }else if (value < prev.getValue()) {
			// 	prev.setLeft(treeNode);
			// }
		}else {
			root = treeNode;
		}		
	}

	public TreeNode delete(TreeNode prev, TreeNode root, int value){
		if(root != null){
			if(root.getValue()==value){
				return root = shift(prev, root);
			}else if (root.getValue()>value) {
				return root.setLeft(delete(root, root.getLeft(), value));
			}else {
				return root.setRight(delete(root, root.getRight(), value));
			}
		}
	}
	public TreeNode shift(TreeNode prev, TreeNode root, int value){
		if(value < prev.getValue()){

			if(root.getRight()==null && root.getLeft==null){
				prev.setLeft(null);
			}else if (root.getRight()!=null&&root.getLeft()!=null) {
				TreeNode right = shift(root.getRight()); 
				prev.setLeft(right);	
			}else if(root.getRight()!=null&&root.getLeft()==null){
				prev.setLeft(root.getRight());
			}else {
				prev.setLeft(root.getLeft());
			}
			
		}else {

		}
	}

	public TreeNode shift(TreeNode root){
		if(root!=null){
			if(root.getRight()==null && root.getLeft()==null){

			}
		}else{
			return null;
		}	
	}

	public void print(int value){
		print(root);
	}
	public void print(TreeNode root){
		if(root!=null){
			print(root.getLeft());
			System.out.print(root.getValue()+" ");
			print(root.getRight());
		}
	}
}