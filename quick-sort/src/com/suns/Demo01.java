package com.suns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 见贤不思齐
 * @time 2018/11/9.
 * @desc 快速排序
 */
public class Demo01 {
    private static final int LENGTH = 2;

    public static void main(String[] args) {
        Integer[] arr = {99,2, 33, 1, 5, 24, 11, 98};

        System.out.println(Arrays.toString(quickSort(arr)));
    }

    private static Integer[] quickSort(Integer[] arr) {
        List<Integer> asList = Arrays.asList(arr);
        Integer[] result = new Integer[arr.length];

        if (asList.size() < LENGTH) {
            result = asList.toArray(result);
        } else {
            int pivot = arr[0];
            List<Integer> little = new ArrayList<>();
            List<Integer> grate = new ArrayList<>();
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] <= pivot) {
                    little.add(arr[i]);
                } else {
                    grate.add(arr[i]);
                }
            }
            Integer[] ltArr = new Integer[little.size()];
            Integer[] gtArr = new Integer[grate.size()];

            ltArr = quickSort(little.toArray(ltArr));
            gtArr = quickSort(grate.toArray(gtArr));
            if (ltArr.length > 0) {
                System.arraycopy(ltArr, 0, result, 0, ltArr.length);
            }
            result[ltArr.length] = arr[0];
            if (gtArr.length > 0) {
                System.arraycopy(gtArr, 0, result, ltArr.length + 1, gtArr.length);
            }
        }
        return result;
    }
}
