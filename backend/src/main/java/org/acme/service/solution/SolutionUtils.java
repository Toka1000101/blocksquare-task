package org.acme.service.solution;

import java.util.List;
import java.util.stream.IntStream;

public class SolutionUtils {

    public static List<Boolean> mapToBooleanList(int[] solution) {
        return IntStream.of(solution)
                .mapToObj(e -> e == 1)
                .toList();
    }
}
