/*
 * AnimalGameModel.java
 * TODO: Keng Low, kl78
 * 
 * Implementation of IAnimalGameModel.
 */
import java.io.*;
import java.util.*;

public class AnimalGameModel implements IAnimalModel {
	
	// In Model-View systems, the Model & View must be able to communicate;
	// this pointer to the view lets you tell the view to do things (show messages,
	// for example).
	private AnimalGameViewer myView;
    private AnimalNode myRoot;
    private AnimalNode myCurrent;
    private AnimalNode myLast;
    private AnimalNode myNewLeaf;
    private StringBuilder myPath;
    private int mySize;
	
	@Override
	public void addNewKnowledge(String question) {
		AnimalNode addedNode = new AnimalNode(question, my)
		
	}

	@Override
	public void addNewQuestion(String noResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(Scanner s) {
		myRoot = readHelper(s);
		String numOfNodes = Integer.toString(mySize);
		String declareNumNodes = "There are "+numOfNodes+" nodes in the tree.";
		myView.showMessage(declareNumNodes);
		myView.setEnabled(true);
		newGame();
	}

	private AnimalNode readHelper(Scanner s) {
		String line = s.nextLine();
		mySize += 1;
		// if line is a leaf
		if (isALeaf(line)) {
			// Construct a leaf AnimalNode from line, and return it.
			AnimalNode leafNode = new AnimalNode(line, null, null);
			return leafNode;
		}
		String lineWithoutQn = line.replace("#Q:", "");
	    // Make a recursive call to read the left subtree.
		AnimalNode leftNode = readHelper(s);
	    // Make a recursive call to read the right subtree.
		AnimalNode rightNode = readHelper(s);
	    // Construct the resulting AnimalNode and return it.
		AnimalNode newNode = new AnimalNode(lineWithoutQn, leftNode, rightNode);
		return newNode;
	}

	private boolean isALeaf(String line) {
		if (line.contains("#Q:")) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public void newGame() {
		myPath = new StringBuilder();
		myPath.append("Your path is displayed below:\n")
		myCurrent = myRoot;
		newQuestion(myCurrent);
	}

	private void newQuestion(AnimalNode node) {
		String nodeToString = node.toString();
		// if node begins with "#Q:"
		if (nodeToString.contains("#Q:")) {
			nodeToString.replace("#Q:", "");
		}
		nodeToString = nodeToString + "?\n";
		myView.update(nodeToString);
	}
	
	@Override
	public void processYesNo(boolean yes) {
		AnimalNode node;
		String question = myCurrent.toString();
		String userAnswer;
		if(yes) {
			node = myCurrent.getYes();
			userAnswer = "YES == ";
		}
		else {
			node = myCurrent.getNo();
			userAnswer = "NO == ";
		}
		userAnswer = userAnswer + question + "\n";
		myPath.append(userAnswer);
		
		// proceeding to next step
		if (node == null && !yes) {
			myView.update(myPath.toString());
			myView.getNewInfoLeaf();
		}
		else if (node == null && yes) {
			myView.showDialog("Game over");
			newGame();
		}
		else {
			myLast = myCurrent;
			myCurrent = node;
			newQuestion(myCurrent);
		}
	}

	@Override
	public void setView(AnimalGameViewer view) {
		myView = view;
	}

	@Override
	public void write(FileWriter writer) throws IOException {
		writeHelper(myRoot, writer);
		writer.close();
	}
	
	public void writeHelper(AnimalNode node, FileWriter writer) throws IOException {
		// See if this node is a leaf
		if (node.getYes() == null && node.getNo() == null) {
			writer.write(node.toString()+"\n");
		}
		else {
			writer.write("#Q:"+node.toString()+"\n");
			if (node.getYes() != null) {
				writeHelper(node.getYes(), writer);
			}
			else if (node.getNo() != null) {
				writeHelper(node.getNo(), writer);
			}
		}
	}
}
