import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SimpleHuffProcessor implements IHuffProcessor {

	private HuffViewer myViewer;
	private HashMap<Integer, String> pathTable = new HashMap<Integer, String>();
	private TreeNode myRoot;
	private int[] myCounts;

	public int compress(InputStream in, OutputStream out, boolean force)
			throws IOException {

		BitOutputStream outStream = new BitOutputStream(out);

		String path;
		int bits;
		int outCount = 0, inCount = 0;
		BitInputStream input = new BitInputStream(in);

		outStream.writeBits(BITS_PER_INT, MAGIC_NUMBER);
		for (int k = 0; k < ALPH_SIZE; k++) {
			outStream.writeBits(BITS_PER_INT, myCounts[k]);
		}
		
		while ((bits = input.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
			inCount += IHuffConstants.BITS_PER_WORD;
			path = pathTable.get(bits);
			outCount += path.length();
			outStream.writeBits(path.length(), strToInt(path));
		}
		input.close();

		if (outCount > inCount) {
			myViewer.showError("Compressed file will be "
					+ (outCount - inCount)
					+ " bits larger. Use forced compression to write to file");
		}

		// Write PSEUDO_EOF
		path = pathTable.get(PSEUDO_EOF);
		outStream.writeBits(path.length(), strToInt(path));
		outCount += path.length();


		input.close();
		outStream.close();

		return outCount;
	}

	public int strToInt(String s) {
		int result = 0;
		for (int i = 0, l = s.length(); i < l; i++) {
			result = (result << 1) + (s.charAt(i) == '1' ? 1 : 0);
		}
		return result;
	}

	public int preprocessCompress(InputStream in) throws IOException {
		myCounts = new int[ALPH_SIZE];
		PriorityQueue<TreeNode> trees = new PriorityQueue<TreeNode>();
		BitInputStream input = new BitInputStream(in);
		int bits;
		while ((bits = input.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
			myCounts[bits] += 1;
		}

		for (int i = 0, l = myCounts.length; i < l; i++) {
			trees.add(new TreeNode(i, myCounts[i]));
		}
		trees.add(new TreeNode(PSEUDO_EOF, 1));

		int count = trees.size();

		while (trees.size() > 1) {
			TreeNode tree1 = trees.remove();
			TreeNode tree2 = trees.remove();
			int combinedWeight = tree1.myWeight + tree2.myWeight;
			trees.add(new TreeNode(0, combinedWeight, tree1, tree2));
		}
		input.close();

		myRoot = trees.remove();
		generatePath(myRoot, "");

		return count;
	}

	private void generatePath(TreeNode root, String path) {
		if (root == null)
			return;
		if (root.myLeft == null && root.myRight == null) {
			pathTable.put(root.myValue, path);
		} else {
			generatePath(root.myLeft, path + "0");
			generatePath(root.myRight, path + "1");
		}
	}

	public void setViewer(HuffViewer viewer) {
		myViewer = viewer;
	}

	public int uncompress(InputStream in, OutputStream out) throws IOException {
		int bits;
		BitInputStream input = new BitInputStream(in);
		BitOutputStream outStream = new BitOutputStream(out);

		bits = input.readBits(BITS_PER_INT);

		if (bits != MAGIC_NUMBER) {
			myViewer.showError("MAGIC_NUMBER not found");
			return -1;
		}

		myCounts = new int[ALPH_SIZE];
		for (int k = 0; k < ALPH_SIZE; k++) {
			bits = input.readBits(BITS_PER_INT);
			myCounts[k] = bits;
		}

		TreeNode current = myRoot;

		int count = 0;

		while ((bits = input.readBits(1)) != -1) {

			if ((bits & 1) == 0) {
				current = current.myLeft;
				count++;

			} else {
				current = current.myRight;
				count++;

			}

			if (current.myLeft == null && current.myRight == null) {
				if (current.myValue == IHuffConstants.PSEUDO_EOF) {

					break; // out of loop
				} else {
					outStream.write(current.myValue);
					count += 1;
					current = myRoot;
				}

			}
		}

		return count;

	}

	private void showString(String s) {
		myViewer.update(s);
	}

}
