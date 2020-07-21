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
        myHashMap.put("555", "null");
        myHashMap.put("666", "null");
        myHashMap.put("777", "null");
        myHashMap.put("888", "null");
        myHashMap.put("999", "null");
        myHashMap.put("000", "null");
        myHashMap.put("001", "null");
        System.out.println(myHashMap.toString());
        System.out.println(myHashMap.table.length);
        myHashMap.put("002", "null");
        myHashMap.put("003", "null");
        myHashMap.put("004", "null");
        System.out.println(myHashMap.toString());
        System.out.println(myHashMap.table.length);
        myHashMap.put("005", "null");
        myHashMap.put("006", "null");
        myHashMap.put("007", "null");
        myHashMap.put("009", "null");
        myHashMap.put("010", "null");
        myHashMap.put("011", "null");
        myHashMap.put("012", "null");
        myHashMap.put("013", "null");
        myHashMap.put("014", "null");
        myHashMap.put("015", "null");
        myHashMap.put("016", "null");
        myHashMap.put("017", "null");
        myHashMap.put("018", "null");
        myHashMap.put("019", "null");
        myHashMap.put("020", "null");
        myHashMap.put("021", "null");
        myHashMap.put("022", "null");
        myHashMap.put("023", "null");
        System.out.println(myHashMap.toString());
        System.out.println(myHashMap.table.length);
        System.out.println(myHashMap.size());

        // System.out.println(myHashMap.get("111"));
        // System.out.println(myHashMap.get("222"));
        // System.out.println(myHashMap.get("333"));
        // System.out.println(myHashMap.get("444"));
        // System.out.println(myHashMap.get("555"));
        // System.out.println(myHashMap.get("666"));
        // System.out.println(myHashMap.get(null));

        MyHashMap<String, String>.MyIterator myIterator = myHashMap.myIterator();
        int count = 0;
        while (myIterator.hasNext()) {
            count++;
            System.out.println(myIterator.next() + ":" + count);
        }


    }
}
