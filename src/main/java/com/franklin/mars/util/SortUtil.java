package com.franklin.mars.util;

public final class SortUtil {


    public static void quickSort(int[] arr, int left, int right) {
        //此处循环只有一个元素 不需要排序 退出
        if (left >= right) {
            return;
        }
        //选中最左边的基准
        int p = arr[left];
        //取出比p大的放到右边，比p小的放到左边，p的下标为i
        int i = left, j = right;
        while (i < j) {
            //向左移动 找到比p小的
            while (arr[j] > p && i < j) {
                j--;
            }
            //向右移动 找到比p大的
            while (arr[i] <= p && i < j) {
                i++;
            }

            //i j 交换
            if (i < j) {
                arr[i] = arr[i] ^ arr[j];
                arr[j] = arr[i] ^ arr[j];
                arr[i] = arr[i] ^ arr[j];
            }
        }
        //交换基准点
        arr[left] = arr[i];
        arr[i] = p;
        //递归调用左侧
        quickSort(arr, left, i - 1);
        //递归调用右侧
        quickSort(arr, i + 1, right);

    }
}
