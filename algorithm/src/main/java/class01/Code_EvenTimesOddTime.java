package class01;

import java.util.HashMap;
import java.util.HashSet;

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
            eor ^= arr[i];//结果就是a^b
        }
        //因为根据位运算可以得到最右为1的值
        int rightOne = eor & (~eor + 1);//可以直接eor&(-eor)，效果一样，取出最右的1,因为两个数不一样，肯定有为1的
        int onlyOne = 0;//eor‘
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                //找对应位置为1的数进行^，就能得到其中一个值了
                onlyOne ^= arr[i];
            }
        }
        //onlyOne因为&完了所有后只剩下一个了，就得到了ab中的一个值
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    //数组中，某个数字出现了k(K<M)次，别的数都出现了M次（M＞1）
    //比如数组中有个数出现了2（K）次，别的都出现了9(M)次
    //取一个32位的int数组，
    // 然后将传入数组的每个元素分解为二进制，并将对应位置为1的数组累加
    //当int的对应位置不为M的整数倍的时候，说明该为出现了指定数字
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        //t[0]标识0位置1出现了几次，以此类推
        for (int num : arr) {
            for (int i = 0; i < 31; i++) {//固定次数，所以复杂度还是O(n)
                t[i] = (num >> i) & 1;//优化版本
//                if (((num >> i) & 1) != 0) {
//                    t[i]++;
//                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m == 0) {
                continue;
            }
            if (t[i] % m != 0 && t[i] % m == k) {
                //通过或运算设置进去
                ans |= (1 << i);
            } else {
                return -1;
            }
        }
        if (ans == 0) {
            int count = 0;
            for (int num :
                    arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    //做一个对数器的算法，用经典的算法实现
    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num :
                arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num :
                map.keySet()) {
            if (map.get(num) == k)
                return num;
        }
        return -1;
    }

    //随机数组生成
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);//这个是出现k次的数
        //以0.5的概率，出现错误的值，方便判断边界条件
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
        //最多这么多种数
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        //k*1+(numKinds-1)*m 一种数出现k次，剩下出现m次，所以数组长度为这个
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;//剩余需要填充的数
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                //保证是新的数
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;//填进去
            }
        }
        //打乱顺序，做个随机
        for (int i = 0; i < arr.length; i++) {
            //整体的随机
            int j = (int) (Math.random() * arr.length);//0~N-1
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }

    //返回随机数，从[-range,+range]
    public static int randomNumber(int range) {
        return (int) ((Math.random() * range) + 1)
                - (int) ((Math.random() * range) + 1);
    }

    public static void main(String[] args) {
        int kinds = 4;//数组中kinds个类型的数据
        int range = 200;//范围在-200~200
        int testTime = 100000;//测试次数
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1;//a 1~9
            int b = (int) (Math.random() * max) + 1;//b 1~9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] array = randomArray(kinds, range, k, m);
            int ans = test(array, k, m);//测方法1
            int ans2 = onlyKTimes(array, k, m);//测方法2
            if (ans != ans2) {
                System.out.println("出现错误");
            }
        }
        System.out.println("测试结束");
    }
}
