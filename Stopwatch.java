import java.lang.*;

public class Stopwatch {
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