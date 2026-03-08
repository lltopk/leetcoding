package org.lyflexi.solutions.baseAlgorithm.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, Integer> helper = new HashMap<>();
        helper.merge(1, 1, (a,b)-> {return a+b;});
        helper.merge(1, 1, Integer::sum);
    }
}
