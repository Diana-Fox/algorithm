package class01;

/**
 * 获得一个局部最小值
 * 前提条件：数组任意相邻数不等
 */
public class Code_BSAwesome {
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //先对比最左侧
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        //再                                                                                                      对比最右侧
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        //都没找到，说明在中间，则二分
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        //因为上面的whil/.e最少俩数，所以要返回一个的处理
        return left;
    }
}
