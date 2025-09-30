package org.algorithms.test.copilot.tutorial;

import java.util.HashMap;
import java.util.Map;

class FieldStore {
    private final Map<String, String> fields = new HashMap<>();

    public void put(String key, String value) {
        fields.put(key, value);
    }

    public Map<String, String> getFields() {
        return fields;
    }
}

class User {
    private final Map<String, String> data;

    public User(Map<String, String> data) {
        this.data = data;
    }

    public void print() {
        System.out.println(data);
    }
}

class CompUserBuilder {
    private final FieldStore store = new FieldStore();

    public CompUserBuilder set(String key, String value) {
        store.put(key, value);
        return this;
    }

    public CompUserBuilder setUsername(String name) {
        store.put("username", name);
        return this;
    }

    public User build() {
        return new User(store.getFields());
    }
}

public class CompositionBuilderTest {
    public static void main(String[] args) {
        User user = new CompUserBuilder()
                        .set("role", "admin")
                        .setUsername("ovidiu")
                        .build();

        user.print(); // should print {role=admin, username=ovidiu}
    }
}