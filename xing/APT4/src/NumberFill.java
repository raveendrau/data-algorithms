public class NumberFill {
    public String[][] myGrid;
    public int myRows, myCols;
    public int lastMax, lastMaxR, lastMaxC;

    public int gradient(String[] picture) {
        myRows = picture.length;
        myCols = picture[0].length();
        myGrid = new String[myRows][myCols];
        int sum = 0;
        for (int r = 0; r < myRows; r++) {
            for (int c = 0; c < myCols; c++) {
                myGrid[r][c] = picture[r].charAt(c) + "";
            }
        }
        
        for (int r = 0; r < myRows; r++) {
            for (int c = 0; c < myCols; c++) {
                char ch = myGrid[r][c].charAt(0);
                if(ch != '.' && ch != 'X' && ch != ' '){
                    lastMaxR=lastMaxC=lastMax = 0;
                    max(r,c);
                    myGrid[lastMaxR][lastMaxC] = "*";
                    sum+=fill(lastMaxR, lastMaxC, lastMax);
                    
                }
            }
        }
        
        return sum;
    }
    
    public void max(int row, int col){
//      System.out.println(row + "-" + col);
        if (row < 0 || col < 0 || row >= myRows || col >= myCols || myGrid[row][col].charAt(0) == 'X' || myGrid[row][col].charAt(0) == '*' ){
            return;
        }
        String c = myGrid[row][col];

        if (c.charAt(0) != '.') {
            int val = Integer.parseInt(""+c);
            if(val > lastMax){
                myGrid[lastMaxR][lastMaxC] = "*";
                lastMaxR = row;
                lastMaxC = col;
                lastMax = val;
            } else if (val == lastMax && col < lastMaxC) {
                myGrid[lastMaxR][lastMaxC] = "*";
                lastMaxR = row;
                lastMaxC = col;
            }
        } else {
            myGrid[row][col] = "*";
        }
        
        max(row-1, col);
        max(row+1, col);
        max(row, col-1);
        max(row, col+1);
    }
    
    
    public int fill(int row, int col, int val){
        
        
        if (row < 0 || col < 0 || row >= myRows || col >= myCols || myGrid[row][col].charAt(0)  == 'X' || myGrid[row][col].charAt(0)  == ' '){
            return 0;
        }
        String c = myGrid[row][col];
        int returnVal = 0;
        if(c.charAt(0) == '*') {
            myGrid[row][col] = " ";
            returnVal = val;
        }
        return returnVal + fill(row-1, col, val)
                + fill(row+1, col, val)
                + fill(row, col-1, val-1)
                + fill(row, col+1, val+1);
    }
    
    public static void main(String[] args){
        NumberFill n = new NumberFill();
        String[] x = {"..X.....", "..X..0..", "1.X.....", "..X.....", "........"};
        System.out.print(n.gradient(x));
        
    }
}
