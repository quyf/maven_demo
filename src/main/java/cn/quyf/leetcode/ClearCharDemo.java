package cn.quyf.leetcode;

import java.util.HashSet;

/**
 * 将连续3个相同的字符消掉，比如 aabbbeddde ->aaee
 *
 * @author quyf
 * @date 2019/11/20 20:23
 */
public class ClearCharDemo {

    public static void main(String[] args) {
        String s = "aabbbedddee";
        System.out.println(solutionTwo(s));
        System.out.println(solutionThree(s));
    }

    public static String solutionFirst(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String first = s.substring(i, i + 1);
            String sub = "";
            if (i + 3 <= s.length()) {
                sub = s.substring(i, i + 3);
                System.out.println("i=" + i + ", sub=" + sub);
            }
            if (sub.equals(first + first + first)) {
                i += 2;
            } else {
                sb.append(first);
            }
        }
        return sb.toString();
    }
    
    public static String solutionTwo(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String first = s.substring(i, i + 1);
            int len = i + 3;
            String sub = "";
            if (i + 3 <= s.length()) {
                sub = s.substring(i, i + 3);
            } else {
                //这个sub的长度小于3 其实不用比较
                sub = s.substring(i, s.length());
            }

            if (sub.equals(first + first + first)) {
                i += 2;
            } else {
                sb.append(first);
            }
        }
        return sb.toString();
    }

    /**
     * 采用了java.lang.String内部的API来做
     * 如果需要深度去重 那么可去掉hashset的逻辑
     * @param s
     * @return
     */
    public static String solutionThree(String s) {
        char[] chars = s.toCharArray();
        //防止循环处理了，只要a字母出现过，则会一次性把aaa都替换调
        HashSet<String> handleChars = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            String aChar = "" + chars[i];
            if(handleChars.contains(aChar)){
                continue;
            }
            handleChars.add(aChar);
            String findChar = aChar + aChar + aChar;
            s = s.replaceAll(findChar, "");
        }
        return s;
    }
    
}
