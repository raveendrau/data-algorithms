

import java.util.*;


public class TrieLexicon implements ILexicon {

	/**
	 * The definition of the Node class in the Trie is below. 
	 * The map children stores references to all the children of a node. 
	 * Provide a reason as to why a map is used 
	 * rather than an array of 52 characters 
	 * (such an array would allow for upper and lower case characters).
	 */
	
	/**
	 * This is an interesting question, 
	 * because common convention gives that 
	 * the performance of an array would be better than a map. 
	 * However, we save space, 
	 * because not all 52 characters would be used; 
	 * whereas they would be in the case of an array. 
	 */
	
    public static class Node {
        String info;
        boolean isWord;
        
        Map<Character,Node> children;
        Node parent;

        Node(char ch, Node p) {
            info = ""+ch;
            isWord = false;
            children = new TreeMap<Character,Node>();
            parent = p;
        }
    }

    protected Node myRoot; // root of entire trie
    protected int mySize;

    public TrieLexicon() {
        myRoot = new Node('x', null);
        mySize = 0;
    }

    public int size() {
        return mySize;
    }

    public void load(ArrayList<String> list){
        for(String s : list) add(s);
    }
    public boolean add(String s) {
        Node t = myRoot;

        for (int k = 0; k < s.length(); k++) {
           
            char ch = s.charAt(k);
            Node child = t.children.get(ch);
            if (child == null) {
               child = new Node(ch, t);
               t.children.put(ch,child);
            }
            t = child;
        }
        
        if (!t.isWord) {
            t.isWord = true; // walked down path, mark this as a word
            mySize++;
            return true;
        }
        return false; // already in set
    }

    public Iterator<String> iterator() {
        ArrayList<String> list = new ArrayList<String>();
        StringBuilder str = new StringBuilder();
        for(Node n : myRoot.children.values()) {
            str.append(n.info);
            fillUp(n,list,str);
            str.deleteCharAt(str.length()-1);
        }
        return list.iterator();
    }
       
    protected void fillUp(Node root, ArrayList<String> list, StringBuilder str){
        if (root.isWord){
            list.add(str.toString());
        }
        for(Node n : root.children.values()) {
            str.append(n.info);
            fillUp(n,list,str);
            str.delete(str.length()- n.info.length(), str.length());
        }
    }

    public void load(Scanner s) {
        while (s.hasNext()){
            add(s.next());
        }
    }

    public LexStatus wordStatus(StringBuilder s){
        Node t = myRoot;
        for (int k = 0; k < s.length(); k++) {
            char ch = s.charAt(k);
            t = t.children.get(ch);
            if (t == null)
                return LexStatus.NOT_WORD; // no path below? done
        }
        return t.isWord ? LexStatus.WORD : LexStatus.PREFIX; 
    }
    public LexStatus wordStatus(String s) {
        return wordStatus(new StringBuilder(s));
    }
    
    public int oneWayCount(){
        return oneWay(myRoot);
    }
    
    protected int oneWay(Node root){
        int count = 0;
        if (root == null) return 0;
        if (root.children.keySet().size() == 1) count = 1;
        for(Node n : root.children.values()){
            count += oneWay(n);
        }
        return count;
    }
    public int nodeCount(){
        return doCount(myRoot);
    }
   
    protected int doCount(Node root){
        int count = 1; // count this node
        if (root == null) return 0;
        for(Node n : root.children.values()){
            count += doCount(n);
        }
        return count;
    }
    
    /**
     * Write a method that returns a copy of the entire trie rooted at a node. 
     * Trie nodes have parent pointers, though they're not shown in the diagram above. 
     * The second parameter to a Trie-Node constructor is the node's parent.
     */
    /**
     * Solution provided below not complete
     */
	private Node copyTrie(Node root) {
		if(root == null) return null;
		Node copy = new Node (root.info.charAt(0),null);
		copy.isWord = root.isWord;
		
		if(!root.children.isEmpty()) {
			for (Node child: root.children.values()) {
				copy.children.put(child.info.charAt(0), copyTrie(child));
			}
		}
		return copy;
	}
	/**
	 * Write a method to traverse all nodes 
	 * in a trie and return the number of words in the trie, 
	 * by referencing the isWord field of each node. 
	 */
	private int wordCount (Node root) {
		if (root == null) return 0;
		int count = 0;
		if (root.isWord) {
			count = 1;
		}
		for (Node child: root.children.values()) {
			count += wordCount(child);
		}
		return count;
	}
	
	/**
	 * Discuss at a high level how to collapse chains of nodes
	 * with single pointers in a trie into one node 
	 * with a longer string labeling it. 
	 * For example, the diagram above would be collapsed 
	 * into the trie shown below. 
	 * In your brief discussion remember,
	 * that the nodes have parent pointers. 
	 * For example, if you get to a leaf in a trie, 
	 * how do you "back up" to a node that can represent 
	 * more than one character as shown below? 
	 * When does that "back up" process stop?
	 */
	
	/**
	 * Traverse a tree, for any node with only one child node, 
	 * we take the value of the child and add it to the parent node. 
	 * We are going to set the grand-child as the child of the new 
	 * compressed node.   
	 */
	
	/**
	 * Boggle  
	 */
	
	/**
	 * Q: If there's no substring, 
	 * e.g., index is past the end of the string, 
	 * what value should the method return and why? 
	 * Write the code for this check.
	 */
	
	/**
	 * A: When you call substring on a word, value should be 0. 
	 * So, base case, you do want to return false. 
	 * You want to get a negative value. 
	 */
	
	/**
	 * Q: If either row or col is outside the board
	 * what value should be returned and why? 
	 * Write the code for this check.
	 */
	
	/**
	 * A: You want to return false, another base case. 
	 */
	
	/**
	 * Q: The call board.getFace(row,col) 
	 * returns the string on the corresponding board-cube. 
	 * Use this call to determine if the first letter 
	 * of the substring starting at index matches the board cell. 
	 * Write code.
	 */
	
	/**
	 * A: 2 things: String w = board.getface(r,c) 
	 * (which gives you a string). 
	 * The other is String x = word.substring(index). 
	 * Return true w.charAt(0) == x.charAt(0). 
	 */
	
	/**
	 * Q: If the boardcell matches the first character, 
	 * you'll need to check whether the boardcell has been used, 
	 * i.e., appears in list. 
	 * Write code using list.contains to check. 
	 * You'll have to create a BoardCell object from (row,col).
	 */
	
	/**
	 * A: What's the value of each recursive base call? 
	 */
	
	/**
	 * Q: How many recursive calls are made 
	 * to check whether the substring can be formed? 
	 * What's the value of index for each recursive call, 
	 * based on the value of index passed in? 
	 * How is the value of parameter list in the 
	 * recursive calls different from the value 
	 * passed into the function making the recursive calls?
	 */
	
	/**
	 * A: As you do more recursive calls, you are checking one letter, 
	 * the index is going to increase by one. 
	 * List is the list of cells visited, we don't want to visit a cell again. 
	 * So at every recursive level, there is going to be an addition to the recursive list. 
	 */
}	
