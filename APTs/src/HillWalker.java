/**
 * 
 * @author Unknown.
 * This is not a product of my work. It is a solution I have come across in my 
 * many travails to solve this problem. As you can see, the work below is pretty
 * sick -- and it took a lot of work and thought that I did not do. 
 * 
 * What I did try, was to understand the approach taken, because I want to know
 * as much as I can learn. The code is only to provide a back-of-the-textbook 
 * answer to the problem. 
 * 
 * It is not my work and should not be considered.
 *
 */
public class HillWalker {
	public int time (int h1,int h2, int thr){
		  if (Math.abs(h1-h2)>thr) return 0;
		  if (h2<=h1) return 1;
		  return (h2-h1)*(h2-h1);
		}
		int[][] afst2 = new int[25][25];
		int[][] afst = new int[25][25];
		int wi;
		int he;
		  int[][][][] dist = new int[25][25][25][25];


		public void fill(int x, int y, int afs){
		  if ((x!=0)||(y!=0))
		    afst[x][y] = afs;
		  if ((x==0)&&(y==0)&&(afs>0)) return;
		  if ((x!=0)&&(dist[x][y][x-1][y]!=0)&&((afst[x-1][y]==0)||(afst[x-1][y]>afs+dist[x][y][x-1][y]))) fill(x-1,y,afs+dist[x][y][x-1][y]);
		  if ((y!=0)&&(dist[x][y][x][y-1]!=0)&&((afst[x][y-1]==0)||(afst[x][y-1]>afs+dist[x][y][x][y-1]))) fill(x,y-1,afs+dist[x][y][x][y-1]);
		  if ((x!=wi-1)&&(dist[x][y][x+1][y]!=0)&&((afst[x+1][y]==0)||(afst[x+1][y]>afs+dist[x][y][x+1][y]))) fill(x+1,y,afs+dist[x][y][x+1][y]);
		  if ((y!=he-1)&&(dist[x][y][x][y+1]!=0)&&((afst[x][y+1]==0)||(afst[x][y+1]>afs+dist[x][y][x][y+1]))) fill(x,y+1,afs+dist[x][y][x][y+1]);
		}

		public void fill2(int x, int y, int afs){
		  if (afst2[x][y]!=-1)
		    afst2[x][y] = afs;
		  if ((x!=0)&&(dist[x][y][x-1][y]!=0)&&((afst2[x-1][y]==0)||(afst2[x-1][y]>afs+dist[x][y][x-1][y]))) fill2(x-1,y,afs+dist[x][y][x-1][y]);
		  if ((y!=0)&&(dist[x][y][x][y-1]!=0)&&((afst2[x][y-1]==0)||(afst2[x][y-1]>afs+dist[x][y][x][y-1]))) fill2(x,y-1,afs+dist[x][y][x][y-1]);
		  if ((x!=wi-1)&&(dist[x][y][x+1][y]!=0)&&((afst2[x+1][y]==0)||(afst2[x+1][y]>afs+dist[x][y][x+1][y]))) fill2(x+1,y,afs+dist[x][y][x+1][y]);
		  if ((y!=he-1)&&(dist[x][y][x][y+1]!=0)&&((afst2[x][y+1]==0)||(afst2[x][y+1]>afs+dist[x][y][x][y+1]))) fill2(x,y+1,afs+dist[x][y][x][y+1]);
		}

		public int distance(int x, int y){
		  for (int i=0; i <he; i++){
		    for (int k=0; k<wi;k++){  
		    afst2[k][i]=0;
		    }
		    }
		  afst2[x][y]=-1;
		  fill2(x,y,0);
		  if (afst2[0][0]==0) afst2[0][0] = 10000000;
		  return afst2[0][0];
		}

		public int highestPoint(String[] landscape, int threshold, int timeToDark) {
		  int[][] h = new int[25][25];
		  wi = landscape[0].length();
		  he = landscape.length;
		  for (int i=0; i <landscape.length; i++){
		    for (int k=0; k<wi;k++){
		      char ch = landscape[i].charAt(k);
		      int hoog = 0;
		      if ((ch>='a')&&(ch<='z')) { hoog = 26 + (ch-'a');} else {hoog = (ch-'A');}
		      h[k][i] = hoog;
		    }
		  }
		  for (int i=0; i <he; i++){
		    for (int k=0; k<wi;k++){
		      if (i!=0) dist[k][i][k][i-1] = time(h[k][i], h[k][i-1],threshold);
		      if (k!=0) dist[k][i][k-1][i] = time(h[k][i], h[k-1][i],threshold);
		      if (i!=he-1) dist[k][i][k][i+1] = time(h[k][i], h[k][i+1],threshold);
		      if (k!=wi-1) dist[k][i][k+1][i] = time(h[k][i], h[k+1][i],threshold);
		    }
		  }
		  afst[0][0]=-1;
		  fill(0,0,0);
		  int best=h[0][0];
		  for (int hei=51; hei>h[0][0];hei--){
		    for (int i=0; i <he; i++){
		      for (int k=0; k<wi;k++){
		        if ((h[k][i]==hei)&&(afst[k][i]<timeToDark)&&(afst[k][i]!=0)){
		          if (afst[k][i]+distance(k,i)<=timeToDark) {
		   //       System.out.println(k+" "+i+" "+afst[k][i]+" "+distance(k,i));
		            return hei;
		          }
		        }
		      }
		    }  
		  }
		  return best;
		} 
}
