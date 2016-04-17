import java.util.*;

public class RowHelper {
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