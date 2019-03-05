package example2;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private Map<String, String> book = new HashMap<>();

    public boolean add(String name, String phone) {
        book.put(name, phone);
    }
}
