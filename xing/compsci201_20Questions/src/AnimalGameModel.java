/*
 * AnimalGameModel.java
 * TODO: YOUR NAME HERE.
 * 
 * Implementation of IAnimalGameModel.
 */
import java.io.*;
import java.util.*;

public class AnimalGameModel implements IAnimalModel {

	// In Model-View systems, the Model & View must be able to communicate;
	// this pointer to the view lets you tell the view to do things (show
	// messages,
	// for example).
	private AnimalGameViewer myView;
	private AnimalNode myRoot;
	private AnimalNode myCurrent;
	private AnimalNode myCurrentParent;
	private int nodeCount;
	private StringBuilder myPath;

	@Override
	public void addNewKnowledge(String question) {
		myCurrentParent.setData(question);
	}

	@Override
	public void addNewQuestion(String noResponse) {
//		myCurrent = myCurrentParent;
		AnimalNode yes = new AnimalNode(noResponse, null, null);
		AnimalNode no = new AnimalNode(myCurrentParent.toString(), null, null);
		myCurrentParent.setNo(no);
		myCurrentParent.setYes(yes);
		nodeCount+=2;
		myView.update("Please enter a differentiating question between the" +
				" two. e.g. Does it have tusks?");
		myView.getDifferentiator();
	}

	@Override
	public void initialize(Scanner s) {
		nodeCount = 0;
		readHelper(s);
		myView.setEnabled(true);
		newGame();
	}

	private AnimalNode readHelper(Scanner s) {
		String line = s.nextLine();
		nodeCount++;
		if (!line.startsWith("#Q:")) {
			myCurrent = new AnimalNode(line, null, null);

			return myCurrent;
		}
		AnimalNode left = readHelper(s);
		AnimalNode right = readHelper(s);
		myCurrent = new AnimalNode(line, left, right);
		myRoot = myCurrent;
		return myCurrent;
	}

	@Override
	public void newGame() {
		myView.showMessage("# node in tree read:" + nodeCount);
		myCurrent = myRoot;
		myCurrentParent = null;
		myView.update(myCurrent.print());
		myPath = new StringBuilder();
		myPath.append("Your path so far:\n");
	

	}

	@Override
	public void processYesNo(boolean yes) {
		myCurrentParent = myCurrent;
		if (yes) {
			myPath.append("You answered YES to " + myCurrent.print() + "\n");
			myCurrent = myCurrent.getYes();
		} else {
			myPath.append("You answered NO to " + myCurrent.print() + "\n");
			myCurrent = myCurrent.getNo();
		}

		if (yes && myCurrent == null) {
			myView.showDialog("I WIN!!!");
		} else if(!yes && myCurrent == null){
			myPath.insert(0, "Would you please tell me what you had in " +
					"mind? Please phrase as a question, e.g, " +
					"Is it a rhinoceros?\n\n");
			myView.update(myPath.toString());
			myView.getNewInfoLeaf();
		} else{
			myView.update(myCurrent.print());
		}
	}

	@Override
	public void setView(AnimalGameViewer view) {
		myView = view;
	}

	@Override
	public void write(FileWriter writer) {
		try {
			writer.write(writeHelper(myRoot));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String writeHelper(AnimalNode n) {
		if (n == null)
			return "";
		else
			return n.toString() + "\n" + writeHelper(n.getYes())
					+ writeHelper(n.getNo());
	}
}
