import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleHuffProcessor implements IHuffProcessor {
    
	private int bRead;
	private int bWrote;
    private HuffViewer view;
    private TreeNode root;
    private HashMap <Integer, String> map;
    private int[] ct = new int[256];
    
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
//        throw new IOException("compress is not implemented");
        
        // initialize number of bits written
    	bWrote = 0;
    	
    	/**
    	 * Write a magic number at the beginning of the compressed file. 
    	 * You can access the IHuffConstants.MAGIC_NUMBER value either 
    	 * without the IHuffConstants modifier in your IHuffProcessor 
    	 * implementation (because the latter interface extends the former) 
    	 * or using the complete IHuffConstants.MAGIC_NUMBER identifier. 
    	 * When you uncompress you'll read this number to ensure you're reading a file your program compressed. Your program should be able to uncompress files it creates. For extra credit you should be able to process standard headers, specified by magic numbers STORE_COUNTS and STORE_TREE in the IHuffConstants interface. There's also a value for custom headers.
    	 */
    	BitOutputStream bos = new BitOutputStream(out);
    	bos.writeBits(BITS_PER_INT, MAGIC_NUMBER);
    	bWrote += BITS_PER_INT; /* Update bits written count */
    	
    	/**
    	 * 
    	 */
        
    }

    public int preprocessCompress(InputStream in) throws IOException {
        throw new IOException("preprocess not implemented");
        //return 0;
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
