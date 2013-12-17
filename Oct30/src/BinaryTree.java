import java.util.Random;
import java.util.Stack;

public class BinaryTree {

	private Node root = null;
	private static Random r = new Random(1234);	
	
	public class Node {
		public int myValue;
		public Node myLeft; // holds smaller tree nodes
		public Node myRight; // holds larger tree nodes

		public Node(int val) { myValue = val; }
	}

	public void add(int newValue)
	{
		if(root == null)
			root = new Node(newValue);
		else
			add(newValue, root);
	}
	
	public void add(int newValue, Node current) {
		if (r.nextInt(2) == 0) {
			if (current.myLeft == null) {
				current.myLeft = new Node(newValue);
			} else {
				add(newValue, current.myLeft);
			}
		} else {
			if (current.myRight == null) {
				current.myRight = new Node(newValue);
			} else {
				add(newValue, current.myRight);
			}
		}
	}


	public String toString()
	{
		return toString(root, "");
	}

	public String toString(Node current, String level) {
		String leftString = "null";
		String rightString = "null";

		if (current.myLeft != null)
			leftString = toString(current.myLeft, level+"   ");
		if (current.myRight != null)
			rightString = toString(current.myRight, level+"   ");

		return current.myValue + "\n" + level +"L: " + leftString + "\n" + level + "R: " + rightString;
	}

	public int computeHeight()
	{
		return computeHeight(root);
	}

	private int computeHeight(Node current) {
		if(current == null)
			return 0;
		int lResult = computeHeight(current.myLeft);
		int rResult = computeHeight(current.myRight);
		if(lResult > rResult) {
			return lResult + 1;
		} else {
			return rResult + 1;
		}
	}

	//return the number of nodes in the tree
	public int countNodes()
	{
		return countNodes(root);
	}

	public int countNodes(Node current)
	{
		if(current == null) {
			return 0;
		}
		int lCount = countNodes(current.myLeft);
		int rCount = countNodes(current.myRight);

		// your code here
		return lCount + rCount + 1;
	}

	public boolean containsNode(int value) {
		return containsNode(value, root);
	}

	private boolean containsNode(int value, Node current) {
		if(current == null){
			return false;
		}
		if(current.myValue == value){
			return true;
		}
		boolean lBool = containsNode(value, current.myLeft);
		boolean rBool = containsNode(value, current.myRight);

		return (lBool || rBool);
	}

	public int findMax() {
		return findMax(root);
	}

	// finds the largest value in the tree
	private int findMax(Node current) {
		int curMax = Integer.MIN_VALUE;
		if(current == null){
			return curMax;
		}
		int lMax = findMax(current.myLeft);
		int rMax = findMax(current.myRight);

		return Math.max(current.myValue, Math.max(lMax, rMax));

	}


	public Stack<Integer> getPathSum(int sum){
		Stack<Integer> path = new Stack<Integer>();
		hasPathSum(path, root, sum);
		return path;
	}

	public boolean hasPathSum(Stack<Integer> path, Node current, int sum){
		//add your base cases here
		
		
		// add current value

		
		//recursion 
		
		
		//backtrack
		
		return false;
	}

	public static void main(String[] args) {
		
		BinaryTree tree = new BinaryTree(); 
		int nodes = (int)Math.pow(2,4);
		for(int i = 0; i < nodes; i++)
			tree.add(r.nextInt(20));
		System.out.println(tree.toString());
		int target = 60;
		Stack<Integer> path = tree.getPathSum(target);
		if(!path.isEmpty()){
			System.out.print("path to " + target + ": ");
			for(Integer i: path){
				System.out.print(i + " ");
			}	
		}
		else{
			System.out.println("no path");
		}
	}


}

