import java.io.*;
import java.util.*;

public class Reader {
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