/**
 * 
 * @author Unknown.
 * This is not a product of my work. It is a solution I have come across in my 
 * many travails to solve this problem. As you can see, the work below is pretty
 * sick -- and it took a lot of work and thought that I did not do. 
 * 
 * What I did try, was to understand the approach taken, because I want to know
 * as much as I can learn. The code is only to provide a back-of-the-textbook 
 * answer to the problem. 
 * 
 * It is not my work and should not be considered.
 *
 */
public class HuffmanDecoding {
	private class Node {
		Node left, right;
		boolean isLeaf = false;
		char ch;
		public Node() {
			left = right = null;
		}
	}
	
	Node root;
	int counter = 0;
	
	public void buildTree(Node parent, String code, int i) {
		if (i == code.length()) {
			parent.isLeaf = true;
			parent.ch = (char) (65 + counter);
			counter++;
			return;
		}
		
		if (code.charAt(i) == '0') {
			if (parent.left == null) {
				parent.left = new Node();
			}
			buildTree(parent.left, code, ++i);
			return;
		}
		if (code.charAt(i) == '1') {
			if (parent.right == null) {
				parent.right = new Node();
			}
			buildTree(parent.right, code, ++i);
			return;
		}
	}
	
	public String decode(String archive, String[] dictionary) { 
		root = new Node();
		String res = "";
		for (String code: dictionary) {
			buildTree(root, code, 0);
		}
		Node temp = root;
		for (int i = 0 ; i< archive.length(); i++) {
			if (archive.charAt(i) == '0')
				temp = temp.left;
			else
				temp = temp.right;
			
			if (temp.isLeaf) {
				res += temp.ch;
				temp = root;
			}
		}
		return res;
	}
}
