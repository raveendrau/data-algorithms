import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleHuffProcessor implements IHuffProcessor {
    
	private int bRead;
	private int bWrote;
    private HuffViewer view;
    private TreeNode root;
    private HashMap <Integer, String> map;
    private int[] ct = new int[256];
    
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
//        throw new IOException("compress is not implemented");
        
    	bWrote = 0; /* Initialize number of bits written */
    	
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
    	bWrote += BITS_PER_INT; /* Update bits written count */
    	
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
			bos.writeBits(BITS_PER_INT, ct[i]);
			bWrote += BITS_PER_INT; /* Update bits written count */
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
        int bit = bis.read();
        
        // If there is an incoming byte
        while (bit > 0) {
			// Get the corresponding encoding from map
        	String code = map.get(bit);
        	// Let's write the encoding for this byte
        	for (int i = 0; i < code.length(); i++) {
        		char c = code.charAt(i);
        		if (c == '0') {
					bos.writeBits(1, 0);
				}
        		else if (c == '1') {
					bos.writeBits(1, 1);
				}
        		bWrote += BITS_PER_INT; /* Update bits written count */
			}
			bit = bis.read(); // read the next byte
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
        bos.writeBits(BITS_PER_INT, PSEUDO_EOF);
        bos.close();
        
        /**
         * If compressing a file results in a file larger than the file 
         * being compressed (this is always possible) then no compressed 
         * file should be created and a message should be shown indicating 
         * that this is the case. 
         * Here's a screen shot from what happens in my program.
         */
        if ((bWrote > bRead) && !force) {
        	int bDiff = bWrote - bRead;
        	String bDiffStr = Integer.toString(bDiff);
			String message = "Compression used "+bDiffStr+" more bits\n"
								+ "I suggest force compression!";
			throw new IOException(message);
		}
        return bWrote;
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
    	Map<Integer, TreeNode> woods = new HashMap<Integer, TreeNode>();
    	bWrote = 0; /* Initialize number of bits written */
        BitInputStream bis = new BitInputStream(in);
        // Return the next byte in the stream as an int, bit
        int bit = bis.read();
        
        while(bit != -1) {
        	bRead += 8;
        	// if a node of the bit already exists
        	if (!woods.containsKey(bit)) {
				TreeNode node = new TreeNode(bit, 1);
				woods.put(bit, node);
			}
        	// if a node of this bit does not exist
        	else {
				TreeNode node = woods.get(bit);
				node.myWeight++;
				woods.put(bit, node);
			}
        	bit = bis.read();
        }
        bis.close();
        
        /**
         * From these counts build the Huffman tree. First create one 
         * node per character, weighted with the number of times the character 
         * occurs, and insert each node into a priority queue. 
         */
        for (TreeNode tree: woods.values()) {
			int character = tree.myValue;
			int weight = tree.myWeight;
			if (character > 0) {
				ct[character] = weight;
			}
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
        
    }

    public void setViewer(HuffViewer viewer) {
        myViewer = viewer;
    }

    public int uncompress(InputStream in, OutputStream out) throws IOException {
        throw new IOException("uncompress not implemented");
        //return 0;
    }
    
    private void showString(String s){
        myViewer.update(s);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
