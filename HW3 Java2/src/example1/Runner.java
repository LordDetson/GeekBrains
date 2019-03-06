package example1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        String[] words = ("Найти и вывести список уникальных слов " +
                "из которых состоит массив дубликаты не считаем " +
                "состоит сколько вывести встречается дубликаты слово").split(" ");
        System.out.println(Arrays.toString(words));
        Map<String, Integer> map = new HashMap<>();
        Integer counter;
        for (String word : words) {
            if ((counter = map.get(word)) != null) {
                map.put(word, ++counter);
            } else {
                map.put(word, 1);
            }
        }
        System.out.println(map);
    }
}
