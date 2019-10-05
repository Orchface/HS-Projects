package src;

import java.io.Serializable;
import java.util.HashMap;

public class Tree implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4167040156277175513L;
	Node root;

	public Tree(Node root) {
		this.root = root;
	}



	public static int height(Node root) {
		if (root == null)
			return 0;
		int h1 = height(root.left);
		int h2 = height(root.right);
		return h2 > h1 ? h2 + 1 : h1 + 1;
	}
	
	public static void genTable(HashMap<Character, String> map, Node node, String prev ){
		
		if(node == null) return;
		
		if(node.left == null && node.right == null){
			map.put(node.character, prev);
			return;
		} else{
			genTable(map, node.left, prev+"0");
			genTable(map, node.right, prev+"1");
		}
		
	}
	
	

}
