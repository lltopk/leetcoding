package org.example;

import java.util.Scanner;

public class LC125_IsPalindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(isPalindrome(scanner.nextLine()));
    }
    public static boolean isPalindrome(String s) {
        if(s==null){
            return false;
        }
        int len = s.length();
        int l = 0,r=len-1;
        while(l<r){
            char cl = s.charAt(l);
            while(l < r &&!isValidChar(cl)){
                cl = s.charAt(++l);
            }

            char cr = s.charAt(r);
            while(l < r &&!isValidChar(cr)){
                cr = s.charAt(--r);
            }

            //大写字母的ascii比小写字母的ascii小
            cl = cl-'A'>=0&&cl-'Z'<=0?(char)(cl+32):cl;
            cr = cr-'A'>=0&&cr-'Z'<=0?(char)(cr+32):cr;

            if(cl!=cr){
                return false;
            }

            l++;
            r--;

        }
        return true;
    }

    private static boolean isValidChar(char c){
        boolean b1 = c-'a'>=0&&c-'z'<=0;
        boolean b2 = c-'A'>=0&&c-'Z'<=0;
        boolean b3 = c-'0'>=0&&c-'9'<=0;
        return b1 || b2 ||b3;
    }
}
