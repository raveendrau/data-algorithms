import java.io.InputStream;


/**
 * Play a game of Boggle. To play code must instantiate the
 * BoggleGui gui object with a working IWordOnBoardFinder and
 * a working ILexicon, as well as a working IAutoPlayer.
 */

public class BoggleMain {

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        
        ILexicon lexicon = new SimpleLexicon();
        IWordOnBoardFinder finder = new BadWordOnBoardFinder();
        
        InputStream is = lexicon.getClass().getResourceAsStream("/ospd3.txt");      
        IAutoPlayer compPlayer = new LexiconFirstAutoPlayer();
        BoggleGUI bgui = new BoggleGUI(lexicon,finder,is, compPlayer);
    }

}
