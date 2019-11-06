package com.example.test.io;

import java.nio.charset.StandardCharsets;

public class TestCharset {
    public void test1() {
        String a = "🤣";
        String b = "12";
        System.out.println(a.length());
        System.out.println(b.length());
        System.out.println(a.codePointAt(0));
        System.out.println(a.codePointCount(0, a.length()));
        System.out.println(b.codePointAt(0));
        System.out.println(b.codePointAt(1));
        System.out.println(a);
        System.out.println(StandardCharsets.UTF_8.encode("🤣").array().length);
        System.out.println(StandardCharsets.UTF_8.encode("1").array().length);
    }
}
