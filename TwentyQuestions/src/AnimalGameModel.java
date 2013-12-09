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
	// this pointer to the view lets you tell the view to do things (show messages,
	// for example).
	private AnimalGameViewer myView;
    
	@Override
	public void addNewKnowledge(String question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewQuestion(String noResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(Scanner s) {
		// TODO Auto-generated method stub
		myView.setEnabled(true);
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processYesNo(boolean yes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setView(AnimalGameViewer view) {
		myView = view;
	}

	@Override
	public void write(FileWriter writer) {
		// TODO Auto-generated method stub
		
	}
}
