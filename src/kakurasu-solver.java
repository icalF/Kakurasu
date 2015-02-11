import java.lang.*;	
import java.io.*;
import java.util.*;

class solver {
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

class Helper {
	protected int size;
	protected int[] hval;
	protected Map<Integer, Vector<Vector<Boolean>>> dict;
	protected Vector<Vector<Boolean>> tmp;
	protected Vector<Boolean> ans;

	public void init(int n, int[] hval) {
		size = n;
		this.hval = hval;
		dict = new HashMap<>();
		ans = new Vector<>();

		for(int i = 0; i < size; ++i) {
			ans.add(false);
		}
	}

	public void push(int n) {
		try {
			int tes = dict.get(n).size();
		} catch (NullPointerException e) {
			dict.put(n, new Vector<Vector<Boolean>>());
			tmp = dict.get(n);

			backtrack(n, 0);
		}
	}

	protected void backtrack(int sum, int pos) {
		try {
			int k = hval[pos];

			backtrack(sum, pos + 1);
			if (sum >= k) {
				ans.set(pos, true);
				backtrack(sum - k, pos + 1);
				ans.set(pos, false);
			}
		} catch (Exception e) {
			if (sum == 0) {
				tmp.add(new Vector());
				for(Boolean b : ans) {
					tmp.lastElement().add(b);
				}
			}
		}
	}

	public Vector<Vector<Boolean>> get(int n) {
		return dict.get(n);
	}
}

class Board {
	protected boolean[][] brd;
	protected int[] hsum, vval, vsum, hval;
	protected int size;
	protected Helper help;
	protected Stopwatch timer;

	public void init(Reader in) throws Exception {
		size = in.nextInt();
		hsum = new int[size];
		vsum = new int[size];
		vval = new int[size];
		hval = new int[size];
		brd = new boolean[size][size];

		for(int i = 0; i < size; ++i) {
			hval[i] = in.nextInt();
		}
		for(int i = 0; i < size; ++i) {
			vval[i] = in.nextInt();
			for(int j = 0; j < size; ++j) {
				brd[i][j] = (in.nextInt() == 1);
			}
			hsum[i] = in.nextInt();
		}
		for(int i = 0; i < size; ++i) {
			vsum[i] = in.nextInt();
		}

		timer = new Stopwatch();

		help = new Helper();
		help.init(size, hval);
		for(int i = 0; i < size; ++i) {
			help.push(hsum[i]);
		}

		backtrack(0);
	}

	protected boolean backtrack(int pos) {
		boolean ans = false;

		if (pos < size) {
			for(Vector<Boolean> v : help.get(hsum[pos])) {
				for(int i = 0; i < size; ++i) {
					brd[pos][i] = v.get(i);
				}

				ans = backtrack(pos + 1);
				if (ans) {
					break;
				}
			}
		} else {
			ans = checkV();
		}

		return ans;
	}

	public void printBoard() {
		printNumber(-1);
		for (int i = 0; i < size; ++i) {
			printNumber(hval[i]);
		}
		printNumber(-1);
		System.out.println();

		for (int i = 0; i < size; ++i) {
			printNumber(vval[i]);
			for (int j = 0; j < size; ++j) {
				if (brd[i][j])
					printNumber(1);
				else
					printNumber(0);
			}
			printNumber(hsum[i]);
			System.out.println();
		}

		printNumber(-1);
		for (int i = 0; i < size; ++i) {
			printNumber(vsum[i]);
		}
		printNumber(-1);
		System.out.println();
	}

	protected void printNumber(int n) {
		if (n < 10) System.out.print(" ");
		if (n < 0) System.out.print(" ");
		else System.out.print(n);
		System.out.print(" ");
	}

	protected boolean checkV() {
		boolean ans = true;
		int sum;

		for(int j = 0; ans && j < size; ++j) {
			sum = 0;

			for(int i = 0; i < size; ++i) {
				if (brd[i][j]) sum += vval[i];
			}

			if (sum != vsum[j]) ans = false;
		}
		return ans;
	}
}

class Stopwatch {
	protected long starttime;
	protected long endtime;

	Stopwatch() {
		starttime = System.currentTimeMillis();
	}

	protected void finalize() throws Throwable {
		endtime = System.currentTimeMillis();

		System.out.println("\nRun in :");
		System.out.println("   " + (double)(endtime - starttime) / 1000.0 + " second(s)");
		System.out.println("on computer :");
		System.out.println("   Intel Core i3 x86 2.3 GHz processor");
		System.out.println("   2 GB memory\n");
		super.finalize();
	}
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input), 
                32768);
        tokenizer = null;
    }

    static String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        	try {
            	tokenizer = new StringTokenizer(reader.readLine());
        	} catch (Exception e) {
        		throw new RuntimeException(e);
        	}
        }
        return tokenizer.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}