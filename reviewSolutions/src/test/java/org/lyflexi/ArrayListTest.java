package org.lyflexi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: ly
 * @Date: 2024/3/26 17:46
 */

/*
*
* //删除 strList 中包含 ch 的所有元素
public static void remove(List<String> strList, char ch) {
    //TODO: implement it
}


示例:
ch='a'
strList={"abc", "abd", "bcd"}

执行之后
strList={"bcd"}
* */
public class ArrayListTest {

    // 推荐：ArrayList使用迭代器执行删除，iterator.remove()为无参
    public static void removeByIterator(List<String> strList, char ch) {
        Iterator<String> iterator = strList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.indexOf(ch) != -1) {
                iterator.remove();
            }
        }
    }

    // 使用普通for循环执行删除，strList.remove(i)传参index
    public static void removeBySelf(List<String> strList, char ch) {
        for (int i = 0; i < strList.size(); i++) {
            if (strList.get(i).indexOf(ch) != -1) {
                strList.remove(i);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        List<String> strList = new ArrayList<>(Arrays.asList("abc", "abd", "bcd"));
        char ch = 'a';
        removeBySelf(strList, ch);
        System.out.println(strList); // Output: [bcd]
    }

}

