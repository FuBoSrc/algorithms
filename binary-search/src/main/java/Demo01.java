import com.suns.utils.SystemUtil;

import java.util.Arrays;

/**
 * @author 见贤不思齐
 * @time 2018/11/8.
 * @desc
 */
public class Demo01 {
    public static void main(String[] args) {
        final int[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int theNum = Integer.valueOf(SystemUtil.scanner("要猜的数字"));
        binarySearch(list, theNum);

    }

    /**
     * 二分法查找（线性查找最多需要 O(N),而二分查找最多需要 Log2(N)次）
     *
     * @param list   待查找的列表（必须是有序的）
     * @param theNum 待查找的数字
     * @return 查找的次数（没有查找到则返回-1）
     */
    private static int binarySearch(final int[] list, final int theNum) {
        int low = 0;
        int high = list.length - 1;
        int mid;
        int guess;
        int times = 0;
        while (low <= high) {
            System.out.println("第" + (++times) + "次");
            mid = (low + high) / 2;
            guess = list[mid];
            if (guess == theNum) {
                System.out.println("Bingo! the number is " + guess);
                System.out.println("共计查找了" + times + "次");
                return times;
            } else if (guess > theNum) {
                high = mid - 1;
                System.out.println(guess + " is too big");
            } else {
                low = mid + 1;
                System.out.println(guess + " is too small");
            }
        }
        System.out.println("共计查找了" + times + "次");
        System.out.println(theNum + "不在" + Arrays.toString(list) + "中");
        return -1;
    }
}
