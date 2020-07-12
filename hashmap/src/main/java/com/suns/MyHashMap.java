package com.suns;

import java.util.Objects;

/**
 * @author : sunshuo
 * @date : 2020-07-12 21:01:02
 * @desc : 自定义HashMap
 */
public class MyHashMap<K, V> {
    private Node<K, V>[] table;

    static class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> next;

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            String s = key + "(" + hash + ")=" + value;
            if (next != null) {
                s += next.toString();
            }
            return s;
        }
    }

    @SuppressWarnings("unchecked")
    public void put(K key, V value) {
        //计算key的hashcode
        int hash = Objects.hashCode(key);
        //指定数组长度，并初始化数组,todo 长度为16的原因
        int length = 16;
        if (table == null) {
            table = new Node[length];
        }

        //根据key的hash值，并确定其数组下标
        int i = hash % length;

        //判断数组元素是否存在
        if (table[i] == null) {
            table[i] = new Node<>(hash, key, value, null);
        } else {
            Node<K, V> node = table[i];
            //key是否重复，key和传入的key值相等
            if ((hash == node.hash) && (Objects.equals(key, node.key))) {
                node.value = value;
            } else {
                //判断next的key是否重复
                for (; ; ) {
                    if (node.next == null) {
                        node.next = new Node<>(hash, key, value, null);
                        break;
                    }

                    if ((hash == node.next.hash) && (Objects.equals(key, node.next.key))) {
                        node.next.value = value;
                        break;
                    }

                    node = node.next;
                }
            }
        }

    }

    public V get(K key) {
        return null;
    }


    @Override
    public String toString() {
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (Node<K, V> node : table) {
            //因为table默认长度为16，如果没放满，会有很多null值
            if (node != null) {
                builder.append(node.toString()).append(",");
            }
        }

        //剔除最后一个逗号
        builder.replace(builder.lastIndexOf(","), builder.lastIndexOf(",") + 1, "");

        return "MyHashMap{" + builder + '}';
    }
}
