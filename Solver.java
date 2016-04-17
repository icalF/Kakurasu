import java.lang.*;	
import java.io.*;

public class Solver {
	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		try {
			Reader.init(new FileInputStream(args[0]));
		} catch (Exception e) {
			Reader.init(System.in);
		}

		Board kaku = new Board();
		kaku.init(in);
		kaku.printBoard();

		System.runFinalizersOnExit(true);
	}
}