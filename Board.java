import java.util.*;

public class Board {
  protected boolean[][] brd;
  protected int[] hsum, vval, vsum, hval;
  protected int size;
  protected RowHelper help;
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

    help = new RowHelper();
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