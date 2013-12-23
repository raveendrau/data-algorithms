import java.util.*;

/**
 * @author keng
 *
 * In implementing the class you’ll write code to remove nodes 
 * with only one child as described below. 
 * A chain of nodes pointed to by one link 
 * can be compressed into a node storing 
 * a suffix rather than a single character. 
 */

public class CompressedTrieLexicon extends TrieLexicon {

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

    public CompressedTrieLexicon() {
        myRoot = new Node('x', null);
        mySize = 0;
    }

    public int size() {
        return mySize;
    }

    /**
     * You’ll need to create a new method compress 
     * to perform this one-child compression, 
     * you’ll call this method in the load method you override as below
     */
    @Override
    public void load(ArrayList<String> list){
        super.load(list);
        compress();
    }
    
    
    /**
     * To compress the trie you’ll write code that finds every leaf. 
     * From each leaf you’ll write code that follows the parent pointers 
     * back up the trie until either a node representing a word is found 
     * or a node that has more than one child is found.
     * 
     * Note that the number of nodes eliminated is 
     * one less than the length of the suffix stored — 
     * we need one node to store the suffix.
     * 
     * The suffix of the single-node-pointing-path is stored 
     * after the parent pointers are followed. 
     * Since the trie nodes store a string, they can certainly store a suffix. 
     * You’ll need to code a new version ofwordStatus 
     * in the CompressedTrie class to recognize when a suffix-node is reached.
     */
    public void compress() {
    	// Initialize list of leaves
    	
    }
    
    public Array
    
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

    /**
     * In particular, note that when a node has nothing below it, 
     * the path to that node represents a word that 
     * isn’t a prefix of another word. 
     * Because of how the TrieLexicon is constructed, 
     * determining if a sequence of characters is a word or a prefix 
     * is fairly straightforward as shown below.
     */
    public LexStatus wordStatus(StringBuilder s){
        Node t = myRoot;
        for (int k = 0; k < s.length(); k++) {
            char ch = s.charAt(k);
            t = t.children.get(ch);
            /**
             * Note that if the path hits a null pointer 
             * the path cannot represent either a prefix or a word 
             * since any pointer out of a node ultimately 
             * reaches a leaf that represents a word that 
             * isn’t a prefix of another word.
             */
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

}
