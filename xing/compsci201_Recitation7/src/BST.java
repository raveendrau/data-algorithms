public class BST {

	TreeNode root = null;

	public class TreeNode {
		public int myValue;
		public TreeNode myLeft; // holds smaller tree nodes
		public TreeNode myRight; // holds larger tree nodes
		public int mySize; // the size of the subtree

		public TreeNode(int val) {
			myValue = val;
		}
	}

	public void add(int newValue) {
		if (root == null)
			root = new TreeNode(newValue);
		else
			add(newValue, root);
	}

	public void add(int newValue, TreeNode current) {
		if (newValue < current.myValue) {
			if (current.myLeft == null) {
				current.myLeft = new TreeNode(newValue);
			} else {
				add(newValue, current.myLeft);
			}
		} else {
			// newValue >= myValue
			if (current.myRight == null) {
				current.myRight = new TreeNode(newValue);
			} else {
				add(newValue, current.myRight);
			}
		}
	}

	public String toString() {
		return toString(root, "");
	}

	public String toString(TreeNode current, String level) {
		String leftString = "null";
		String rightString = "null";

		if (current.myLeft != null)
			leftString = toString(current.myLeft, level + "   ");
		if (current.myRight != null)
			rightString = toString(current.myRight, level + "   ");

		return current.myValue + "\n" + level + "L: " + leftString + "\n"
				+ level + "R: " + rightString;
	}

	public int computeHeight() {
		return computeHeight(root);
	}

	private int computeHeight(TreeNode current) {
		if (current == null)
			return 0;
		int lResult = computeHeight(current.myLeft);
		int rResult = computeHeight(current.myRight);
		if (lResult > rResult) {
			return lResult + 1;
		} else {
			return rResult + 1;
		}
	}

	// public static int height(TreeNode t){
	//
	// if (t == null) return 0;
	// return 1 + Math.max(height(t.myLeft), height(t.myRight));
	// }
	//
	// public static int diameter(TreeNode t) {
	//
	// if (t == null) return 0;
	//
	// int leftD = diameter(t.myLeft);
	// int rightD = diameter(t.myRight);
	// int rootD = height(t.myLeft) + height(t.myRight) + 1;
	//
	// return Math.max(rootD, Math.max(leftD, rightD));
	// }
	/**
	 * Return both height and diameter of t, return height as value with index 0
	 * and diameter as value with index 1 in the returned array.
	 * 
	 * @param t
	 *            is a binary tree
	 * @return array containing both height (index 0) and diameter (index 1)
	 */
	public static int[] diameterHelper(TreeNode t) {

		int[] ret = new int[2]; // return this array

		if (t == null) {
			ret[0] = 0; // height is 0
			ret[1] = 0; // and diameter is 0
			return ret;
		}
		int[] left = diameterHelper(t.myLeft);
		int[] right = diameterHelper(t.myRight);

		ret[0] = 1 + Math.max(left[0], right[0]); // this is height
		ret[1] = Math.max(left[0] + right[0] + 1, Math.max(left[1], right[1]));
		// fill in value for ret[1] below
		return ret;
	}

	public static int diameter(TreeNode t) {
		int[] ret = diameterHelper(t);
		return ret[1];
	}

	public int countNodes() {
		return countNodes(root);
	}

	public int countNodes(TreeNode current) {
		if (current == null) {
			return 0;
		}
		int lCount = countNodes(current.myLeft);
		int rCount = countNodes(current.myRight);

		return 1 + lCount + rCount;
	}

	public boolean containsNode(int value) {
		return containsNode(value, root);
	}

	private boolean containsNode(int value, TreeNode current) {
		// your code goes here
		// hint: base case...if the node is null, it does not contain the value
		if (current == null)
			return false;
		if (current.myValue == value)
			return true;
		boolean lBool = containsNode(value, current.myLeft);
		boolean rBool = containsNode(value, current.myRight);
		return lBool || rBool;
	}

	public int findMax() {
		return findMax(root);
	}

	private int findMax(TreeNode current) {
		// assuming all nodes have values greater than -1000
		int max = -1000;
		if (current == null)
			return max;
		int lMax = findMax(current.myLeft);
		int rMax = findMax(current.myRight);
		return Math.max(current.myValue, Math.max(lMax, rMax));
	}

	public int numLeaves() {
		return numLeaves(root);
	}

	private int numLeaves(TreeNode current) {
		if (current.myLeft == null && current.myRight == null)
			return 1;
		int numLeaves = 0;
		if (current.myLeft != null)
			numLeaves += numLeaves(current.myLeft);
		if (current.myRight != null)
			numLeaves += numLeaves(current.myRight);
		return numLeaves;
	}

	public int levelCount(int target) {
		return levelCount(root, target);
	}

	/**
	 * Returns number of nodes at specified level in t, where level >= 0.
	 * 
	 * @param level
	 *            specifies the level, >= 0
	 * @param t
	 *            is the tree whose level-count is determined
	 * @return number of nodes at given level in t
	 */
	public int levelCount(TreeNode t, int level) {
		if (t == null)
			return 0;
		if (level == 0)
			return 1;

		return levelCount(t.myLeft, level - 1)
				+ levelCount(t.myRight, level - 1);

	}

	public boolean hasPathSum(int target) {
		return hasPathSum(root, target);
	}

	/**
	 * Return true if and only if t has a root-to-leaf path that sums to target.
	 * 
	 * @param t
	 *            is a binary tree
	 * @param target
	 *            is the value whose sum is searched for on some root-to-leaf
	 *            path
	 * @return true if and only if t has a root-to-leaf path summing to target
	 */
	private boolean hasPathSum(TreeNode current, int target) {
		if (current == null)
			return false;
		if (current.myLeft == null && current.myRight == null)
			return current.myValue == target;
		else
			return hasPathSum(current.myLeft, target - current.myValue)
					|| hasPathSum(current.myRight, target - current.myValue);
	}

	public int findK(int k) {
		return findK(root, k);
	}

	private int findK(TreeNode current, int k) {
		if (current == null)
			return 0;
		if (current.myLeft == null && current.myRight == null) {
			k--;
			if (k == 0)
				return current.myValue;
		} else {
			if (findK(current.myLeft, k) != -1)
				return findK(current.myLeft, k);
			k = k - countNodes(current.myLeft);
			k--;
			if (k == 0)
				return current.myValue;
			return findK(current.myRight, k);
		}

		return -1;
	}

	/**
	 * Returns true if s and t are isomorphic, i.e., have same shape.
	 * 
	 * @param s
	 *            is a binary tree (not necessarily a search tree)
	 * @param t
	 *            is a binary tree
	 * @return true if and only if s and t are isomorphic
	 */

	public static boolean isIsomorphic(TreeNode s, TreeNode t) {
		if (s == null && t == null)
			return true;
		else if (s == null || t == null)
			return false;
		else {
			return isIsomorphic(s.myLeft, t.myLeft)
					&& isIsomorphic(s.myRight, t.myRight);
		}
	}

	/**
	 * Returns true if s and t are quasi-isomorphic, i.e., have same
	 * quasi-shape.
	 * 
	 * @param s
	 *            is a binary tree (not necessarily a search tree)
	 * @param t
	 *            is a binary tree
	 * @return true if and only if s and t are quasi-isomporphic
	 */
	public static boolean isQuasiIsomorphic(TreeNode s, TreeNode t) {

		if (s == null && t == null)
			return true;
		else if (s == null || t == null)
			return false;
		else {
			return (isQuasiIsomorphic(s.myLeft, t.myLeft) && isQuasiIsomorphic(s.myRight, t.myRight))
					|| (isQuasiIsomorphic(s.myRight, t.myLeft) && isQuasiIsomorphic(s.myLeft, t.myRight));
		}
	}

	public static void main(String[] args) {
		BST bst = new BST();
		int[] data = { 6, 8, 2, 4, 1, 7, 5, 3, 9 };
		for (int i : data)
			bst.add(i);
		System.out.print(bst.toString());
		System.out.printf("\nNumber of leaves: %d\n", bst.numLeaves()); // 5
		System.out
				.printf("Number of nodes on level 2: %d\n", bst.levelCount(2)); // 4
		System.out.println("Has path sum 17: " + bst.hasPathSum(17));// true
		System.out.println("Has path sum 10: " + bst.hasPathSum(10));// false
		System.out.printf("The value in node 5 is: %d\n", bst.findK(5)); // 5
		System.out.println("The diameter is : " + diameter(bst.root)); // 6

	}
}
