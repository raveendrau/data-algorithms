import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
		BitInputStream input = new BitInputStream(in);

		String path;
		int bits;
		int outCount;

		outStream.writeBits(BITS_PER_INT, MAGIC_NUMBER);
		writeHeader(outStream);

		outCount = writeCompressed(outStream, input);

		return outCount;
	}

	private int writeCompressed(BitOutputStream outStream, BitInputStream input)
			throws IOException {
		String path;
		int bits;
		int outCount = 0, inCount = 0;
		while ((bits = input.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
			inCount += IHuffConstants.BITS_PER_WORD;
			path = pathTable.get(bits);
			outCount += path.length();
			outStream.writeBits(path.length(), strToInt(path));
		}

		if (outCount > inCount) {
			myViewer.showError("Compressed file will be "
					+ (outCount - inCount)
					+ " bits larger. Use forced compression to write to file");
		}

		// Write PSEUDO_EOF
		path = pathTable.get(PSEUDO_EOF);
		outStream.writeBits(path.length(), strToInt(path));
		outCount += path.length();

		outStream.flush();
		return outCount;
	}

	private void writeHeader(BitOutputStream outStream) {
		for (int k = 0; k < ALPH_SIZE; k++) {
			outStream.writeBits(BITS_PER_INT, myCounts[k]);
		}
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
		BitInputStream input = new BitInputStream(in);
		int bits;
		while ((bits = input.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
			myCounts[bits] += 1;
		}

		int count = makeTree();

		generatePath(myRoot, "");
		return count;
	}

	private int makeTree() {
		PriorityQueue<TreeNode> trees = new PriorityQueue<TreeNode>();
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

		myRoot = trees.remove();
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

		if (bits == MAGIC_NUMBER) {
			readHeader(input);
			makeTree();
			readCompressed(input, outStream);
		} else if (bits == WHEELER_MAGIC) {
			untransform(input, outStream);
		} else {
			myViewer.showError("MAGIC_NUMBER not found");
			return -1;
		}

		return 0;
	}

	private int readCompressed(BitInputStream input, BitOutputStream outStream) throws IOException {
		int count = 0;
		int bits;
		TreeNode current = myRoot;
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

	private void readHeader(BitInputStream input) throws IOException {
		int bits;
		myCounts = new int[ALPH_SIZE];
		for (int k = 0; k < ALPH_SIZE; k++) {
			bits = input.readBits(BITS_PER_INT);
			myCounts[k] = bits;
		}
	}

	private void showMessage(ArrayList<String> list) {
		myViewer.update(list);
	}

	public int transform(InputStream in, OutputStream out) throws IOException {
		BitInputStream bis = new BitInputStream(in);
		BitOutputStream bos = new BitOutputStream(out);
		int bitCount = 0;

		BurrowsWheeler bw = new BurrowsWheeler();
		while (true) {
			char[] chunk = bw.transform(bis);
			if (chunk.length == 0)
				break;
			chunk = bw.mtf(chunk);
			byte[] array = new byte[chunk.length];
			for (int k = 0; k < array.length; k++) {
				array[k] = (byte) chunk[k];
			}
			System.out.println(chunk.length);
			ByteArrayInputStream bas = new ByteArrayInputStream(array);

			int first = bw.getFirst();
			// write header information as appriopriate, e.g.,
			// magic-number and first
			bos.writeBits(BITS_PER_INT, WHEELER_MAGIC);
			bos.writeBits(BITS_PER_INT, first);
			preprocessCompress(bas);
			writeHeader(bos);
			int count = makeTree();

			generatePath(myRoot, "");
			BitInputStream comp = new BitInputStream(new ByteArrayInputStream(
					array));
			bitCount += writeCompressed(bos, comp);

		}
		bos.flush();
		return bitCount;
	}

	public int untransform(BitInputStream bis, OutputStream out)
			throws IOException {
		BurrowsWheeler bw = new BurrowsWheeler();
		int chunkCount = 1;
		// int header = bis.readBits(BITS_PER_INT);
		// if (header == WHEELER_MAGIC){
		// chunkCount++;
		// }

		while (true) {
			int first = bis.readBits(BITS_PER_INT); // read first index
			if (first == -1) {
				myViewer.showError("problem getting first index");
				break;
			} else {
				System.out.println(first);
			}
			
			
			
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			BitOutputStream temp = new BitOutputStream(byteOut);
			readHeader(bis);
			readCompressed(bis, temp);
			// uncompress(bis,temp);
			byte[] array = byteOut.toByteArray();
			char[] chunks = new char[array.length];
			for (int k = 0; k < chunks.length; k++) {
				byte b = array[k];
				char bp = (char) (b & 0xff);
				System.out.println(bp);
				chunks[k] = bp;
			}

			// TODO: write code here:
			// now that you have a char[] first call unmove-to-front
			chunks = bw.unmtf(chunks);
			// then call decode to untransform (you'll need first to do this)
			chunks = bw.decode(chunks, first);
			// then write out each char to the OutputStream out that's a
			// parameter

			for (char c : chunks) {
				out.write(c);
			}
			
			int header = bis.readBits(BITS_PER_INT);
			if (header == WHEELER_MAGIC) {
				chunkCount++;
				System.out.println("magic");
			} else {
				myViewer.showError("problem getting magic");
				break;
			}
		}
		out.flush();
		return chunkCount;
	}

}
