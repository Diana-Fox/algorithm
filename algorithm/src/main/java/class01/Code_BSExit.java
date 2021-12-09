package class01;

/**
 * 二分查找某个数是否存在
 */
public class Code_BSExit {
    public static boolean exits(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) {//L.R至少两个数
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }

        }
        //因为至少两个数，所以需要验一下一个数的
        return sortedArr[L] == num;
    }
}
