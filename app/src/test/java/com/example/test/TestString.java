package com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestString {
    @Test
    public void test(){
        String aString = "我饿了，即使我不阿嘎嘎地方发动机噶；大严格";
        for (int i = 0; i < aString.length();) {
            int codePoint = aString.codePointAt(i);
            if (Character.isSupplementaryCodePoint(codePoint)) {
                i += 2;
            } else {
                ++i;
            }
            System.out.println(Character.toChars(codePoint));
        }
        System.out.println();
        for (int i = 0; i < aString.length(); i++) {
            char codePoint = aString.charAt(i);
            System.out.println(codePoint);
        }
    }
}
