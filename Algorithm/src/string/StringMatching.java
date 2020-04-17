package string;

import java.util.Arrays;

/**
 * 字符串问题，判断一个字符串是否在另一个字符串中存在，若存在，返回第一个字符下标，若不存在。返回-1
 * <p>
 * Author Mayiwei
 * Date 2020/4/16
 */
public class StringMatching {


    /**
     * 暴力解法
     *
     * @param str1
     * @param str2
     * @return index 不存在返回-1
     */
    public static int violentSolution(String str1, String str2) {
        //先转化为字符数组
        char[] charArr1 = str1.toCharArray();
        char[] charArr2 = str2.toCharArray();
        //双指针先分别指向两个字符数组的第0位
        int i = 0;
        int j = 0;
        while (i < charArr1.length && j < charArr2.length) {
            //如果第一位相等了，比较下一位，以此类推
            if (charArr1[i] == charArr2[j]) {
                i++;
                j++;
            } else {
                //如果不相等，i置到下一个位置，j置到初始位置重新比较
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == charArr2.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * kmp解法，思路流程和暴力算法一致，暴力算法是不匹配，向右移动一格再比较
     * kmp解法为： 若不匹配，则把指针i向右移动j - next[j - 1]位进行比对
     * @param str1
     * @param str2
     * @param next
     * @return index 不存在返回 -1
     */
    public static int kmpSolution(String str1, String str2, int[] next) {
        //先转化为字符数组
        char[] charArr1 = str1.toCharArray();
        char[] charArr2 = str2.toCharArray();
        //双指针先分别指向两个字符数组的第0位
        int i = 0;
        int j = 0;
        while (i < charArr1.length && j < charArr2.length ) {
            //如果第一位相等了，比较下一位，以此类推
            if (charArr1[i] == charArr2[j]) {
                i++;
                j++;
            } else {
                if(j == 0){
                    i = i + 1;
                }else{
                    i = i - j + (j - next[j - 1]);

                }
                //如果不相等，i置到下一个位置，j置到初始位置重新比较
                j = 0;
            }
        }
        if (j == charArr2.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * kmp解法
     * @param str1
     * @param str2
     * @param next
     * @return
     */
    public static int kmpSolution2(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //如果不匹配，从next[j - 1]获取新的j，直到匹配退出
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取str2的next数组，值为前缀和后缀的公共字符长度
     * 比如说ABCDABD
     * 数组就是[0,0,0,0,1,2,0]
     * @param str
     * @return
     */
    private static int[] getNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) {
            //如果不匹配，从next[j - 1]获取新的j，直到匹配退出
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            //匹配时就把j加1
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;

        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "asdfasdfp";
        String str2 = "asdf";
        System.out.println(Arrays.toString(getNext(str2)));
        int index = kmpSolution(str1, str2, getNext(str2));
        System.out.println(index);
    }
}
