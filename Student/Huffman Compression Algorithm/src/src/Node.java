package src;

import java.io.Serializable;

public class Node implements Comparable<Node>, Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 8796392792217929627L;
		char character;
		int weight;
		
		Node left;
		
		Node right;

		@Override
		public String toString(){
			if(this.character == '\u0000')
			{
				return this.weight+"\u0001";
			} else{
				return this.character+","+this.weight;
			}
		}
		
		public Node(char c, int i, Node left, Node right){
			this.character = c;
			this.weight = i;
			this.left = left;
			this.right = right;
		}
		
		
		@Override
		public int compareTo(Node arg0) {
			return this.weight - arg0.weight;
		}

	}