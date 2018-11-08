package com.suns;

import java.util.Arrays;

/**
 * @author 见贤不思齐
 * @time 2018/11/8.
 * @desc
 */
public class Demo01 {
    public static void main(String[] args) {
        int[] arr = {32, 3, 21, 545, 2, 31, 7, 343};
        System.out.println(Arrays.toString(arr));
        int length = arr.length;
        int[] newArr = new int[length];
        int index;
        for (int i = 0; i < length; i++) {
            index = findSmallestIndex(arr);
            newArr[i] = arr[index];
            arr = deleteArr(arr, index);
        }
        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 删除数组指定位置的元素
     *
     * @param arr   待操作的数组
     * @param index 待删除的位置
     * @return 操作后的数组
     */
    private static int[] deleteArr(int[] arr, int index) {
        int[] result = new int[arr.length - 1];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            result[j] = arr[i];
            j++;
        }
        return result;
    }

    /**
     * 获取数组中最小的值
     *
     * @param arr 待排序数组
     * @return 数组中最小的值
     */
    private static int findSmallest(int[] arr) {
        int smallest = arr[0];
        for (int a : arr) {
            if (smallest > a) {
                smallest = a;
            }
        }
        return smallest;
    }


    /**
     * 获取数组中最小的索引
     *
     * @param arr 待排序数组
     * @return 数组中最小的索引
     */
    private static int findSmallestIndex(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[index] > arr[i]) {
                index = i;
            }
        }
        return index;
    }

}
