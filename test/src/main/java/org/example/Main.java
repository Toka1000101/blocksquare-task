
import org.example.LightsOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        // Initial board configuration
        int[][] board = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        int[] a = {1,4,0};
        List<Boolean> b =IntStream.of(a)
                .mapToObj(e -> e == 1)
                .collect(Collectors.toList());
        System.out.println(b);

        int[][] board7 = {
                {0, 0, 1, 1, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 1, 1}
        };

        int[][] boardNoSolution5 = {
                {0, 1, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };

        int[][] board5 = {
                {0, 0, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 1}
        };

        LightsOut lo = new LightsOut(board5);
        int[] sol = lo.solve();

        System.out.println(Arrays.toString(sol));


    }
}
