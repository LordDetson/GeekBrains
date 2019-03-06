package example2;

import java.util.Set;

public interface Phonebook {
    void add(String name, String phone);

    Set<String> get(String name);
}
