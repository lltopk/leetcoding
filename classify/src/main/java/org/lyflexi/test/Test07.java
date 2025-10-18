package org.lyflexi.test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Test07 {
    public static void main(String[] args) {
        Map<Student, Integer> map = new TreeMap<>(new Comparator<Student>() {
            public int compare(Student p1, Student p2) {
                return p1.score - p2.score ;
            }
        });
        map.put(new Student("Tom", 77), 1);
        map.put(new Student("Bob", 66), 2);
        map.put(new Student("Lily", 99), 3);
        for (Student key : map.keySet()) {
            System.out.println(key);
        }
        System.out.println(map.get(new Student("Bob", 66)));
    }
    static class Student {
        public String name;
        public int score;
        Student(String name, int score) {
            this.name = name;
            this.score = score;
        }
        public String toString() {
            return String.format("{%s: score=%d}", name, score);
        }
    }

}