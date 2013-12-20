import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;


public class Maze extends JFrame{
	private int myPixel = 25;
	private Color[][] myMaze;
	private Color[] myColorMap;
	private void init(){
		initMaze();
		initColorMap();
		
		int[] start = {0, 1};
		int[] end = {19, 19};
		calculatePath(start, end);

	}

	private void initMaze(){
		myMaze = new Color[20][20];
		Random r = new Random(1234);
		for(int i = 0; i < myMaze.length; i++){
			for(int j = 0; j < myMaze[i].length; j++){
				myMaze[i][j] = Color.lightGray;
			}
		}
		
		for(int i = 0; i < 200; i++){
			myMaze[r.nextInt(20)][r.nextInt(20)] = Color.blue;
		}

		myMaze[0][1] = Color.green;
		myMaze[19][19] = Color.green;
	}
	
	private void initColorMap(){
		myColorMap = new Color[256];
		int r = 0;
		int g = 0; 
		int b = 0;
		for(int i = 0; i < 256; i++){
			myColorMap[i] = new Color(r, g, b);
			if(r < 255){
				r += 3;
			}
			else if(g < 255){
				g += 3;
				r = 255;
			}	
			else{
				b += 3;
				g = 255;
			}
		}
	}

	private boolean calculatePath(int[] start, int[] end){
		//try to get from [0,1] to [19,19]
		if (start == end){
			return true;
		}
		
		if (start[0] > 19 || start[0] < 0 || start[1] > 19 || start[1] < 0){
			return false;
		}
		
		if (myMaze[start[0]][start[1]] == Color.blue || myMaze[start[0]][start[1]] == Color.orange){
			return false;
		}
		
		int[] rdelta = { -1,-1,-1, 0, 0, 1, 1, 1 };
		int[] cdelta = { -1, 0, 1,-1, 1,-1, 0, 1 };
		
		if(start[0] != 0 && start[1] != 1){
			myMaze[start[0]][start[1]] = Color.orange;
		}
		
		for (int k = 0; k < rdelta.length; k++) {
			
			
			start[0] += rdelta[k];
			start[1] += cdelta[k];

			
			if (calculatePath(start, end)){
				return true;
			}
			
		}
		
		myMaze[start[0]][start[1]] = Color.lightGray;
		
		return false;
	}

	private void drawGrid(Graphics g){
		g.setColor(Color.white);
		for(int i=0; i < 500; i+=myPixel){
			g.drawLine(0, i, 500, i);
			g.drawLine(i, 0, i, 500);
		}
	}

	private void drawMaze(Graphics g){
		for(int i = 0; i < myMaze.length; i++){
			for(int j = 0; j < myMaze[i].length; j++){
				g.setColor(myMaze[i][j]);
				g.fillRect(i*myPixel, j*myPixel, myPixel, myPixel);
			}
		}
	}


	public void paint(Graphics g){
		
		drawMaze(g);
		drawGrid(g);
		repaint();
	}

	public static void main(String[] args){
		Maze frame = new Maze();
		frame.init();
		frame.setTitle("Maze");
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}