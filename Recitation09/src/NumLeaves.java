public class NumLeaves
/* counts the number of leaves using an int method
 * produces a binary search tree using Comparable
 *
 */
{
	@SuppressWarnings("unused")
	public static void main(String[] asd)
	{
		Tree t = new Tree();
		String s = "qwertyuiop";
		int len = s.length();
		t.bst(s);
		t.infix();
		System.out.println("number of leaves is: "+ t.numLeaves());;
	}
}

class Tree
{
	private class Node
	{
		Object info;
		Node left, right;
		@SuppressWarnings("unused")
		int balance;
		
		public String toString()
		{
			return "<" + info + ">";
		}
	}
	 private Node root;
	 
	 public int numLeaves()
	 {
	 	return numLeaves(root);
	 }
	 
	 private boolean isLeaf(Node t)
	 {
	 	return t.left == null && t.right == null;
	 }
	 
	 private int numLeaves(Node t)
	 {
	 	if(t != null)
	 	{
	 		if(isLeaf(t) )
	 		{
	 			return 1;
	 		}
	 		else//non-leaf
	 		{
	 			return numLeaves(t.left) + numLeaves(t.right);
	 		}
	 	}
	 	return 0;
	}
	 
	 private Node subtree(Object obj)
	 {
	 	 Node temp = new Node();
	 	 temp.info = obj;
	 	 temp.right = null;
	 	 temp.left = null;
	 	 return temp;
	 }
	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public void bst(String s)
	 {
	 	int len = s.length();
	 	Node p = null, q ;//p will chase q
	 	root = subtree( new Character(s.charAt(0) ) );
	 	Comparable item;
	 	char ch;
	 	for(int j = 1; j < len; j++)
	 	{
	 		q = root;
	 		ch = s.charAt(j);
	 		item = new Character(ch);
	 		while(q != null)
	 		{
	 			p = q;
	 			if(item.compareTo(q.info) > 0 )
	 			{
	 				q = q.right;
	 			}
	 			else
	 			{
	 				q = q.left;
	 			}
	 		}//q = null, now insert node to p.left or p.right
	 		if(item.compareTo(p.info) > 0)
	 		{
	 			p.right = subtree(item);
	 		}
	 		else
	 		{
	 			p.left = subtree(item);
	 		}
	 	}
	 }
	 
	 public void infix()
	 {
	 	infix(root);
	 	System.out.println();	 		
	 }
	 	
	 private void infix(Node t)
	 {
	 	if(t != null)
	 	{
	 	    infix(t.left);
	 		System.out.print(t);
	 		infix(t.right);
	 	}
	 }
	 
	 public void copy(Tree t)
	 {
	 	root = copy(t.root);
	 }
	 
	 private Node copy(Node t)
	 {
	 	    Node temp = null;
	 	    if(t != null)
	 	    {	 	    	
	 			temp = new Node();
	 			temp.info = t.info;
	 			temp.left = copy(t.left);
	 			temp.right = copy(t.right);
	 		}
	 		return temp; 		
	 }
	 
}
	 						
	 		
	 	 
	 