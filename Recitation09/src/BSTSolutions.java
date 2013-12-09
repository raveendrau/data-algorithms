public class BSTSolutions {

	TreeNode root = null;

	public class TreeNode {
		public int myValue;
		public TreeNode myLeft; // holds smaller tree nodes
		public TreeNode myRight; // holds larger tree nodes

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
		return toString(root, "")+"\n";
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

	public int countNodes() {
		return countNodes(root);
	}

	public int countNodes(TreeNode current) {
		if (current == null) {
			return 0;
		}
		int lCount = countNodes(current.myLeft);
		int rCount = countNodes(current.myRight);

		// your code here
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
		if (current.myLeft == null && current.myRight == null) {
			// we are a leaf
			return 1;
		}

		int leafCount = 0;

		if (current.myLeft != null)
			leafCount += numLeaves(current.myLeft);

		if (current.myRight != null)
			leafCount += numLeaves(current.myRight);

		return leafCount;
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

		if (t == null) {
			return 0;
		}

		if (level == 0) {
			return 1;
		}

		int nodeCount = 0;

		nodeCount += levelCount(t.myLeft, level - 1);
		nodeCount += levelCount(t.myRight, level - 1);

		return nodeCount;

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
		if (current == null) {
			// Node is null, not a path
			return false;
		} else if (current.myLeft == null && current.myRight == null) {
			// current is a leaf
			if (target == current.myValue) {
				// and its value matches
				return true;
			}
		}

		// so far we aren't null, and aren't a complete path.
		// check our children

		boolean rightPath = hasPathSum(current.myRight, target - current.myValue);
		boolean leftPath = hasPathSum(current.myLeft, target - current.myValue);

		// true if either child is in a path.
		return rightPath || leftPath;
	}

	public int findK(int k) {
		return findK(root, k);
	}

	// Here we are looking for the kth smallest node in the tree
	//
	// Note: This method could fail if nodes contain negative values 
	//	(especially -1)... so assume all values are positive (like in the test)
	private int findK(TreeNode current, int k) {
		if (current == null) {
			// current is null, can't be kth smallest node.
			return -1;
		}
		if (current.myLeft == null && current.myRight == null) {
			// we are a leaf. 
			// our k is the k passed to us minus 1 (we have to count ourselves).
			k--;
			if (k == 0) {
				// if k == 0, then this is the kth smallest node.
				return current.myValue;
			}
      return -1; // this is a leaf, but not the right k.
		} else {
			// not a leaf. Check the left.
			int val = findK(current.myLeft, k);
			if (val != -1) {
				// wasn't -1, so they had the kth smallest. Return it.
				return val;
			}
			// our k is the k we were given minus the number of nodes left of us (they came first)
			k = k - countNodes(current.myLeft);
			// still subtract 1 to count our selves as well
			k--;
			if (k == 0)
				// we are the kth node!
				return current.myValue;

			// we weren't the kth node... check the right subtree
			//
			// Note: if this is -1, then it means that the kth node isn't in this tree
			//	So we'll return -1 also.
			//	If it did have the kth smallest this won't be -1, and we'll return that.
			return findK(current.myRight, k);
		}

	}

	public static void main(String[] args) {
		BSTSolutions bst = new BSTSolutions();
		int[] data = { 6, 8, 2, 4, 1, 7, 5, 3, 9 };
		for (int i : data)
			bst.add(i);
		System.out.print(bst.toString());
		System.out.printf("Number of leaves: %d\n", bst.numLeaves()); // 5
		System.out
				.printf("Number of nodes on level 2: %d\n", bst.levelCount(2)); // 4
		System.out.println("Has path sum 17: " + bst.hasPathSum(17));// true
		System.out.println("Has path sum 10: " + bst.hasPathSum(10));// false
		System.out.printf("The value in node 5 is: %d\n", bst.findK(5)); // 5

	}
}
