package example2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhonebookImpl implements Phonebook {
    private Map<String, Set<String>> map = new HashMap<>();

    @Override
    public void add(String name, String phone) {
        Set<String> phones;
        if (!map.containsKey(name)) {
            phones = new HashSet<>();
            phones.add(phone);
            map.put(name, phones);
        } else {
            phones = map.get(name);
            phones.add(phone);
        }
    }

    @Override
    public Set<String> get(String name) {
        return map.get(name);
    }

    public static void main(String[] args) {
        Phonebook phonebook = new PhonebookImpl();
        phonebook.add("Дмитрий", "+375293808409");
        phonebook.add("Дмитрий", "+375293808409");
        phonebook.add("Дмитрий", "+375293838483");
        phonebook.add("Никита", "+375253333333");
        System.out.println(phonebook.get("Дмитрий"));
        System.out.println(phonebook.get("Никита"));
    }
}
