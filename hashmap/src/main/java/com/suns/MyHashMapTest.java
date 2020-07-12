package com.suns;

/**
 * @author : sunshuo
 * @date : 2020-07-12 22:15:02
 * @desc : MyHashMap 测试类
 */
public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.put("111", "aaa");
        myHashMap.put("222", "bbb");
        myHashMap.put("333", "ccc");
        myHashMap.put("444", "ddd");
        myHashMap.put(null, "eee");
        myHashMap.put("555", null);
        System.out.println(myHashMap.toString());
    }
}
