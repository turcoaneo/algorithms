package org.algorithms.test.copilot.tutorial;

import java.util.HashMap;
import java.util.Map;

class Builder<T extends Builder<T>> {
    protected Map<String, String> fields = new HashMap<>();

    public T set(String key, String value) {
        fields.put(key, value);
        //noinspection unchecked
        return (T) this;
    }
}

class ChildBuilder extends Builder<ChildBuilder> {
    public ChildBuilder setChild(String name) {
        fields.put("child", name);
        return this;
    }
}

class UserBuilder extends Builder<UserBuilder> {
    public UserBuilder setUsername(String name) {
        fields.put("username", name);
        return this;
    }

    @Override
    public UserBuilder set(String key, String value) {
        fields.put(key, value.toUpperCase());
        return this;
    }
}

public class FluentBuilderTest {
    public static void main(String[] args) {
        UserBuilder ub = new UserBuilder()
                .set("role", "admin")
                .setUsername("ovidiu");
        ChildBuilder cb = new ChildBuilder()
                .set("role", "pupil")
                .setChild("prodigy");

        System.out.println(ub.fields); // should print {role=ADMIN, username=ovidiu}
        System.out.println(cb.fields); // should print {role=pupil, child=prodigy}
    }
}