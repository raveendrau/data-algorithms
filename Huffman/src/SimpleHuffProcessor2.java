import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class SimpleHuffProcessor2 implements IHuffProcessor {
   
    private HuffViewer myViewer;
    private TreeNode myRoot;
    private HashMap <Integer, String> myMap = new HashMap<Integer, String>();
    private int[] myCounts;
    
    public int compress(InputStream in, OutputStream out, boolean force) 
    		throws IOException {
//        throw new IOException("compress is not implemented");
       String path;
       int bits;
       int myIn = 0; /* Update count bits written and read */
       int myOut = 0; /* Update count bits written and read *
    	
    	/**
    	 * Write a magic number at the beginning of the compressed file. 
    	 * You can access the IHuffConstants.MAGIC_NUMBER value either 
    	 * without the IHuffConstants modifier in your IHuffProcessor 
    	 * implementation (because the latter interface extends the former) 
    	 * or using the complete IHuffConstants.MAGIC_NUMBER identifier. 
    	 * When you uncompress you'll read this number to ensure you're 
    	 * reading a file your program compressed. 
    	 * Your program should be able to uncompress files it creates. 
    	 * For extra credit you should be able to process standard headers, 
    	 * specified by magic numbers STORE_COUNTS and STORE_TREE in the 
    	 * IHuffConstants interface. There's also a value for custom headers.
    	 */
    	BitOutputStream bos = new BitOutputStream(out);
    	bos.writeBits(BITS_PER_INT, MAGIC_NUMBER);
    	
    	/**
    	 * Write information after the magic number that allows the 
    	 * Huffman tree to be recreated. The simplest thing to do here is 
    	 * write ALPH_SIZE counts as int values, but you can also 
    	 * write the tree. Writing the counts will create a header in 
    	 * standard count format or SCF. This is a header of 255 counts, 
    	 * one 32-bit int value for each 8-bit chunk, in order from 0-255. 
    	 * You don't need a count for pseudo-EOF because it's one.
    	 */
        for (int i = 0; i < ALPH_SIZE; i++) {
			bos.writeBits(BITS_PER_INT, myCounts[i]);
		}
        
        /**
         * Write the bits needed to encode each character of the input file. 
         * For example, if the coding for 'a' is "01011" then your code 
         * will have to write 5 bits, in the order 0, 1, 0, 1, 1 
         * every time the program is compressing/encoding the chunk 'a'. 
         * You'll re-read the file being compressed, look up each 
         * chunk/character's encoding and print a 0 or 1 bit for 
         * each '0' or '1' character in the encoding
         */
        BitInputStream bis = new BitInputStream(in);
        // Return the next byte in the stream as an int, bit
        
        
        // If there is an incoming byte
        while ((bits = bis.readBits(IHuffConstants.BITS_PER_WORD)) != 1) {
        	myIn = IHuffConstants.BITS_PER_WORD;
			// Get the corresponding encoding from map
        	path = myMap.get(bits);
        	myOut = path.length();
        	// Let's write the encoding for this byte
        	bos.writeBits(path.length(), getInt(path));
		}
        bis.close();
        
        /**
         * To avoid this problem, there are two solutions: store the 
         * number of real bits in the header of the compressed file 
         * or use a pseudo-EOF character whose Huffman-coding is written 
         * to the compressed file. Then when you read the compressed file 
         * your code stops when the encoding for the pseudo-EOF character 
         * is read. The pseudocode below shows how to read a compressed file 
         * using the pseudo-EOF technique.
         */
        path = myMap.get(PSEUDO_EOF);
        bos.writeBits(path.length(), getInt(path));
        myOut += path.length();
        
        bis.close();
        bos.close();
        
        /**
         * If compressing a file results in a file larger than the file 
         * being compressed (this is always possible) then no compressed 
         * file should be created and a message should be shown indicating 
         * that this is the case. 
         * Here's a screen shot from what happens in my program.
         */
        if ((myOut > myIn) && !force) {
        	int myDiff = myOut - myIn;
        	String DiffStr = Integer.toString(myDiff);
			String message = "Compression used "+DiffStr+" more bits\n"
								+ "I suggest force compression!";
			myViewer.showError(message);
		}
        return myOut;
    }

    /**
     * Get the integer representation of a string
     * @param s
     * @return integer equivalent
     */
    public int getInt(String str) {
    	int ret = 0;
    	for (int i = 0; i < str.length(); i++) {
			ret = (ret << 1) + (str.charAt(i) == '1' ? 1 : 0);
		}
    	return ret;
    }
    
    /**
     * To compress a file, count how many times every bit-sequence occurs 
     * in a file. These counts are used to build weighted nodes that will be 
     * leaves in the Huffman tree. Although this writeup sometimes refers to 
     * "characters", you should use int variables/valuse in your code 
     * rather than char. Note that the method for reading bits-at-a-time 
     * from a BitInputStream returns an int, so using int variables makes 
     * sense (this might be different in the Burrows-Wheeler code you write.) 
     * Any wording in this write-up that uses the word character means an 8-bit 
     * chunk and this chunk-size could (in theory) change. Do not use any 
     * variables of type byte in your program. Use only int variables, or 
     * perhaps char variables if you implement the Burrows-Wheeler project.
     */
    public int preprocessCompress(InputStream in) throws IOException {
//        throw new IOException("preprocess not implemented");
    	myCounts = new int[ALPH_SIZE];
    	int bits;
        BitInputStream bis = new BitInputStream(in);
        // Return the next byte in the stream as an int, bit
        
        while((bits = bis.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
        	myCounts[bits] += 1;
        }
 
        
        /**
         * From these counts build the Huffman tree. First create one 
         * node per character, weighted with the number of times the character 
         * occurs, and insert each node into a priority queue. 
         */
        PriorityQueue<TreeNode> q = new  PriorityQueue<TreeNode>();
        for (int i = 0; i < myCounts.length; i++) {
			q.add(new TreeNode(i, myCounts[i]));
        }        
       TreeNode eof = new TreeNode(PSEUDO_EOF, 1);
       q.add(eof);
       
       int size = q.size();
       while (size > 1){
    	   TreeNode boyTree = q.remove();
    	   TreeNode girlTree = q.remove();
    	   int newWeight = boyTree.myWeight + girlTree.myWeight;
    	   q.add(new TreeNode(0, newWeight, boyTree, girlTree));		
       }
       bis.close();
       
       /**
        * Create a table or map of 8-bit chunks (represented as an int value) 
        * to Huffman-codings. The map of chunk-codings is formed by traversing 
        * the path from the root of the Huffman tree to each leaf. Each 
        * root-to-leaf path creates a chunk-coding for the value stored 
        * in the leaf. When going left in the tree append a zero to the path; 
        * when going right append a one. The map has the 8-bit int chunks as 
        * keys and the corresponding Huffman/chunk-coding String as the value 
        * associated with the key.
        */
       myRoot = q.remove();
       getPath(myRoot, "");
       
       return size; 
    }

    /**
     * First create one node per character, weighted with the number of 
     * times the character occurs, and insert each node into a priority 
     * queue. Then choose two minimal nodes, join these nodes together 
     * as children of a newly created node, and insert the newly created 
     * node into the priority queue (see notes from class). 
     * The new node is weighted with the sum of the two minimal nodes 
     * taken from the priority queue. Continue this process until 
     * only one node is left in the priority queue. 
     * This is the root of the Huffman tree.
     */
    public TreeNode growTree(PriorityQueue<TreeNode> q) {
    	TreeNode t;
    	if (q.size() == 1) {
			t = q.poll();
		}
    	else {
			TreeNode treeBoy = q.poll();
			TreeNode treeGirl = q.poll();
			TreeNode treeBaby = new TreeNode(treeBoy.myValue*1000, 
					treeBoy.myWeight+treeGirl.myWeight, treeBoy, treeGirl);
			q.add(treeBaby);
			t = growTree(q);
		}
    	return t;
    }
    
    public void getPath(TreeNode t, String s) {
    	if (t.isLeaf()) {
			myMap.put(t.myValue, s);
		}
    	else {
			getPath(t.myLeft,s+"0");
			getPath(t.myRight,s+"0");
		}
    }
    
    public void setViewer(HuffViewer viewer) {
        myViewer = viewer;
    }

    public int uncompress(InputStream in, OutputStream out) throws IOException {
        int bits;
        BitInputStream bis = new BitInputStream(in);
        BitOutputStream bos = new BitOutputStream(out);
        
        /**
         * When you uncompress you'll read this number to ensure you're 
         * reading a file your program compressed. Your program should be 
         * able to uncompress files it creates. For extra credit you should be 
         * able to process standard headers, specified by magic numbers 
         * STORE_COUNTS and STORE_TREE in the IHuffConstants interface. 
         * There's also a value for custom headers.
         * 
         * In general, a file with the wrong magic number should not 
         * generate an error, but should notify the user. For example, 
         * in my program the exception above ultimately causes the user 
         * to see what's shown below. This is because the exception is caught 
         * and the viewer's showError method called appropriately. 
         * Your code should at least print a message, and ideally generate 
         * an error dialog as shown.
         */
        bits = bis.readBits(BITS_PER_INT);
        if (bits != MAGIC_NUMBER) {
        	myViewer.showError("magic number not right");
        	return -1;
		}
        
        /**
         * Write information after the magic number that allows the Huffman 
         * tree to be recreated. The simplest thing to do here is write 
         * ALPH_SIZE counts as int values, but you can also write the tree. 
         * Writing the counts will create a header in standard count format or 
         * SCF. This is a header of 255 counts, one 32-bit int value for each 
         * 8-bit chunk, in order from 0-255. You don't need a count for 
         * pseudo-EOF because it's one.
         */
        myCounts = new int[ALPH_SIZE];
        for (int i = 0; i < ALPH_SIZE; i++) {
			bits = bis.readBits(BITS_PER_INT);
			myCounts[i] = bits;
		}
        
        /**
         * Write the bits needed to encode each character of the input file. 
         * For example, if the coding for 'a' is "01011" then your code will 
         * have to write 5 bits, in the order 0, 1, 0, 1, 1 every time the 
         * program is compressing/encoding the chunk 'a'. You'll re-read the 
         * file being compressed, look up each chunk/character's encoding and 
         * print a 0 or 1 bit for each '0' or '1' character in the encoding.
         */
        TreeNode node = myRoot;
        int count = 0;
        
		while ((bits = bis.readBits(1)) != -1) {
			if ((bits & 1) == 0) {
				node = node.myLeft;
				count++;
			} 
			else {
				node = node.myRight;
				count++;
			}
			if (node.myLeft == null && node.myRight == null) {
				if (node.myValue == IHuffConstants.PSEUDO_EOF) {
					break; 
				} 
				else {
					bos.write(node.myValue);
					count += 1;
					node = myRoot;
				}
			}
		}
        
        bis.close();
        bos.close();
        return count;
    }
    
    private void showString(String s){
        myViewer.update(s);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    
