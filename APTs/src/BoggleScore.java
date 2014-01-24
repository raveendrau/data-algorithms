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

public class BoggleScore {

    public long bestScore(String[] grid, String[] words) {
        char g[][] = new char[4][4];
        for (int i = 0; i < 4; i++) {
            g[i] = grid[i].toCharArray();
        }
        long res = 0;

        for (String w : words) {
            long a[][] = new long[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (g[i][j] == w.charAt(0)) {
                        a[i][j] = 1;
                    }
                }
            }
            for (int t = 1; t < w.length(); t++) {
                long b[][] = new long[4][4];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        for (int di = -1; di <= 1; di++) {
                            for (int dj = -1; dj <= 1; dj++) {
                                if (di == 0 && dj == 0) continue;
                                int ni = i + di;
                                int nj = j + dj;
                                if (ni < 0 || ni > 3 || nj < 0 || nj > 3) continue;
                                if (w.charAt(t) == g[ni][nj]) {
                                    b[ni][nj] = sum(b[ni][nj], a[i][j]);
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        a[i][j] = b[i][j];
                    }
                }
            }
            long cnt = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    cnt = sum(cnt, a[i][j]);
                }
            }

            res = sum(res, mul(cnt, w.length() * w.length()));
        }
        return res;
    }

    private long mul(long y, int x) {
        long r = y * x;
        return r > MOD ? r % MOD : r;
    }

    static final long MOD = 10000000000000l;

    private long sum(long y, long x) {
        long r = y + x;
        return r > MOD ? r % MOD : r;
    }

}
