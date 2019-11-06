package com.example.test;

import org.junit.Test;

public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start;
        int end;
        int maxStart = 0;
        int maxEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            start = i;
            end = i + 1;

            for (; start >= 0 && end < s.length(); ) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                start--;
                end++;
            }

            start++;
            end--;

            if (end - start + 1 > maxEnd - maxStart + 1) {
                maxStart = start;
                maxEnd = end;
            }

            start = i;
            end = i;
            for (; start >= 0 && end < s.length(); ) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                start--;
                end++;
            }

            start++;
            end--;

            if (end - start + 1 > maxEnd - maxStart + 1) {
                maxStart = start;
                maxEnd = end;
            }


        }
        return s.substring(maxStart, maxEnd + 1);
    }

    @Test
    public void test1() {
        String result = longestPalindrome("bbdbbb");
        System.out.println(result);
    }
}
