package example1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class CalculatorOfDataArray {

    private static int availableProcessors = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);

    public static Double[] calc(Double[] array, BinaryOperator<Double> formula) {
        for (int i = 0; i < array.length; i++) {
            array[i] = formula.apply(array[i], (double) i);
        }
        return array;
    }

    public static Double[] parallelCalcUseParallelStream(Double[] array, BinaryOperator<Double> formula) {
        int subSize = array.length / availableProcessors;
        List<Double[]> buf = SplitJoinArrays.split(array, subSize);
        List<Double[]> collect = buf.parallelStream()
                .map(doubles -> calc(doubles, formula))
                .collect(Collectors.toList());
        return SplitJoinArrays.join(collect);
    }

    public static Double[] parallelCalcUseExecutorService(Double[] array, BinaryOperator<Double> formula) throws ExecutionException, InterruptedException {
        int subSize = array.length / availableProcessors;
        List<Double[]> buf = SplitJoinArrays.split(array, subSize);

        List<Double[]> result = buf.parallelStream()
                .map(doubles -> executor.submit(() -> calc(doubles, formula)))
                .collect(Collectors.toList()).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());

        return SplitJoinArrays.join(result);
    }

    private static class Test {
        private static final BinaryOperator<Double> formula = (value, index) ->
                value * Math.sin(0.2 + index / 5) * Math.cos(0.2 + index / 5) * Math.cos(0.4 + index / 2);

        private static int size = 5000000;
        private static Double[] array = new Double[size];

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            Double value = 1d;
            Arrays.fill(array, value);

            long startTime = System.currentTimeMillis();
            CalculatorOfDataArray.calc(array.clone(), formula);
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("calc: " + duration);

            startTime = System.currentTimeMillis();
            CalculatorOfDataArray.parallelCalcUseParallelStream(array.clone(), formula);
            duration = System.currentTimeMillis() - startTime;
            System.out.println("parallelCalcUseParallelStream: " + duration);

            startTime = System.currentTimeMillis();
            CalculatorOfDataArray.parallelCalcUseExecutorService(array.clone(), formula);
            duration = System.currentTimeMillis() - startTime;
            System.out.println("parallelCalcUseExecutorService: " + duration);

            System.exit(0);
        }
    }
}
