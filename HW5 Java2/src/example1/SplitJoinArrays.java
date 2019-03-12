package example1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitJoinArrays {
    public static List<Double[]> split(Double[] array, int subSize) {
        int countSplit = (int) Math.ceil(array.length / (double) subSize);
        List<Double[]> result = new ArrayList<>();
        for (int i = 0; i < countSplit; i++) {
            if (countSplit - 1 == i && subSize * (i + 1) > array.length) {
                result.add(Arrays.copyOfRange(array, subSize * i, array.length));
                break;
            }
            result.add(Arrays.copyOfRange(array, subSize * i, subSize * (i + 1)));
        }
        return result;
    }

    public static Double[] join(List<Double[]> list) {
        return list.stream().reduce(SplitJoinArrays::merge).orElse(null);
    }

    private static Double[] merge(Double[] arr_1, Double[] arr_2) {
        int len_1 = arr_1.length, len_2 = arr_2.length;
        int a = 0, b = 0, len = len_1 + len_2;
        Double[] result = new Double[len];
        for (int i = 0; i < len; i++) {
            if (b < len_2 && a < len_1) {
                if (arr_1[a] > arr_2[b]) result[i] = arr_2[b++];
                else result[i] = arr_1[a++];
            } else if (b < len_2) {
                result[i] = arr_2[b++];
            } else {
                result[i] = arr_1[a++];
            }
        }
        return result;
    }
}