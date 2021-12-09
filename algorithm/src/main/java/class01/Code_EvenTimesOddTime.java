package class01;

/**
 * 使用亦或得到数组中的特殊数
 */
public class Code_EvenTimesOddTime {
    //arr中，只有一种数出现奇数次
    public static void printOddTinesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    //arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (~eor + 1);//可以直接eor&(-eor)，效果一样，取出最右的1,因为两个数不一样，肯定有为1的
        int onlyOne = 0;//eor‘
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {//找对应位置为1的数进行^，就能得到其中一个值了
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }
    //数组中，某个数字出现了k(K<M)次，别的数都出现了M次（M＞1 ）
    public static void printOddTimesNum3(int[] arr){

    }
}
