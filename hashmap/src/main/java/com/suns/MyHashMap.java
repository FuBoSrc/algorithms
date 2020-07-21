package com.suns;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author : sunshuo
 * @date : 2020-07-12 21:01:02
 * @desc : 自定义HashMap
 */
public class MyHashMap<K, V> {
    public Node<K, V>[] table;

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * table中非空的实际长度
     */
    private static int size;
    public static int threshold;

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
        //指定数组长度，并初始化数组,长度为16的原因
        //length 的长度设置一定是 2^n ，这样可以保证 length-1 的各个位全是1
        //为什么要保证 length的各个位全是1呢？
        // 例如 长度取15 即 1110，那么无论如何最后一位的与操作一定都是0，那么实际上能取到数组下标只有8种，足足浪费了7位
        int length = DEFAULT_INITIAL_CAPACITY;
        if (table == null) {
            threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
            table = new Node[length];
        }

        //根据key的hash值，并确定其数组下标
//        int i = hash % length;
        //采用 & 操作来进行优化数组下标计算,C = A & B，则 C <= A 且 C <= B，例如 1010 & 1111 = 1010,
        //首先 & 操作的性能是很高的，并且通过与 length-1 进行按位与操作后，可以确定数组下边的范围一定是在 0~length-1中
        int i = hash & (length - 1);
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
        size++;
        if (size >= threshold) {
            resize();
            System.out.println("开始扩容");
        }
    }


    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        //扩容
        int newCapacity = table.length << 1;
        threshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);
        Node<K, V>[] newTable = new Node[newCapacity];
        //转移数据
        for (Node<K, V> oldNode : table) {
            if (oldNode == null) {
                continue;
            }

            while (oldNode != null) {
                Node<K, V> next = oldNode.next;
                //计算新下标
                int i = oldNode.hash & (newCapacity - 1);
                Node<K, V> newNode = oldNode;
                if (newTable[i] == null) {
                    newNode.next = null;
                } else {
                    newNode.next = newTable[i];
                }

                newTable[i] = newNode;
                oldNode = next;
            }


            /*while (oldNode != null) {
                //计算新下标
                int i = oldNode.hash & (newCapacity - 1);
                Node<K, V> newNode = oldNode;
                if (newTable[i] == null) {
                    newNode.next = null;
                } else {
                    newNode.next = newTable[i];
                }
                newTable[i] = newNode;
                oldNode = oldNode.next;//此种写法有问题，因为newNode中间有指向oldNode的操作，直接如此操作会产生死循环？
            }*/
        }

        //替换table
        table = newTable;
    }

    public V get(K key) {
        //计算hash
        int hash = Objects.hashCode(key);
        //计算table数组下标
//        int i = hash % table.length;
        int i = hash & (table.length - 1);
        Node<K, V> node = table[i];
        if (node == null) {
            return null;
        }

        //判断 key 是否相等
        if ((hash == node.hash) && (Objects.equals(key, node.key))) {
            return node.value;
        } else {
            for (; ; ) {
                if (node.next == null) {
                    return null;
                }

                if ((hash == node.next.hash) && (Objects.equals(key, node.next.key))) {
                    return node.next.value;
                }

                node = node.next;
            }

        }
    }


    @Override
    public String toString() {
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

    public MyIterator myIterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<K> {
        private Node<K, V> current;
        private Node<K, V> next;
        /**
         * table的下标
         */
        private int index;

        public MyIterator() {
            current = null;
            next = null;
            index = 0;
            //获取第一个非空的table元素
            if (table == null || size <= 0) {
                //没有任何元素
                return;
            }

            for (; ; ) {
                if (index >= table.length) {
                    //已经遍历完成，下标越界
                    break;
                }

                next = table[index++];
                if (next != null) {
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public K next() {
            //是否需要这样去进行判断呢？
            if (table == null) {
                return null;
            }

            //处理current
            current = next;
            //处理next
            if (current.next != null) {
                // 当前索引的链表下一个元素不为空
                next = current.next;
            } else {
                //当前索引位置链表无下一个元素，需要找下一个不为空的索引位置
                for (; ; ) {
                    if (index >= table.length) {
                        //已经遍历完成，下标越界
                        break;
                    }
                    next = table[index++];
                    if (next != null) {
                        break;
                    }
                }
            }
            return current.key;
        }
    }

}
