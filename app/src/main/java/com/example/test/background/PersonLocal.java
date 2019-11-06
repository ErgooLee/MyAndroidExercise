package com.example.test.background;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class PersonLocal<T> {
    private static final Map<PersonLocalKey, Object> values = new HashMap<>();

    public void set(@NonNull Person person, @NonNull T attribute) {
        values.put(new PersonLocalKey(person, attribute.getClass()), attribute);
    }

    public T get(@NonNull Person person, Class<T> attributeType) {
        @SuppressWarnings("unchecked")
        T ret = (T) values.get(new PersonLocalKey(person, attributeType));
        return ret;
    }

    public static void testPersonLocal() {
        PersonLocal<Gender> genderPersonLocal = new PersonLocal<>();

        Person bob = new Person();
        genderPersonLocal.set(bob, Gender.MALE);
        System.out.println("bob is " + genderPersonLocal.get(bob, Gender.class));

        Person mei = new Person();
        genderPersonLocal.set(mei, Gender.FEMALE);
        System.out.println("mei is " + genderPersonLocal.get(mei, Gender.class));

        //内存泄漏

    }

}

class Person {

}

enum Gender {
    MALE,
    FEMALE,
}

class PersonLocalKey {
    @NonNull
    private final Person person;
    @NonNull
    private final Class<?> attribute;

    PersonLocalKey(@NonNull Person person,
                   @NonNull Class<?> attribute) {
        this.person = person;
        this.attribute = attribute;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + System.identityHashCode(person);
        result = result * 31 + System.identityHashCode(attribute);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PersonLocalKey) {
            PersonLocalKey other = ((PersonLocalKey) obj);
            return other.person == this.person && other.attribute == this.attribute;
        }
        return super.equals(obj);
    }
}

