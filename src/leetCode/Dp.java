package leetCode;

public class Dp {


    class Solution {
        public String longestPalindrome(String s) {
            int len = s.length();
            // 特判
            if (len < 2) {
                return s;
            }

            int maxLen = 1;
            int begin = 0;

            // 1. 状态定义
            // dp[i][j] 表示s[i...j] 是否是回文串


            // 2. 初始化
            boolean[][] dp = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                dp[i][i] = true;
            }

            char[] chars = s.toCharArray();
            // 3. 状态转移
            // 注意：先填左下角
            // 填表规则：先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
            for (int j = 1; j < len; j++) {
                for (int i = 0; i < j; i++) {
                    // 头尾字符不相等，不是回文串
                    if (chars[i] != chars[j]) {
                        dp[i][j] = false;
                    } else {
                        // 相等的情况下
                        // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                        if (j - i < 3) {
                            dp[i][j] = true;
                        } else {
                            // 状态转移
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }

                    // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                    // 此时更新记录回文长度和起始位置
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
            // 4. 返回值
            return s.substring(begin, begin + maxLen);
        }


        //最长上升子序列
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0)
                return 0;
            int length = nums.length;
            //dp[i] 以i结尾的最大递增子序列
            int[] dp = new int[nums.length];
            for (int i = 0; i < length; i++) {
                dp[i] = 1;
            }

            for (int i = 1; i < length; i++) {
                int maxDp = dp[i];
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j])
                        maxDp = Math.max(dp[j] + 1, maxDp);
                }
                dp[i] = maxDp;
            }
            int max = dp[0];
            for (int i = 1; i < length; i++) {
                max = Math.max(max, dp[i]);
            }
            return max;
        }

        //最长上升子序列
        public int longestCommonSubsequence(String text1, String text2) {
            if (text1 == null || text2 == null)
                return 0;
            if (text1.length() == 0 || text2.length() == 0)
                return 0;
            char[] char1 = text1.toCharArray();
            char[] char2 = text2.toCharArray();
            //dp[i][j] 长度为i的串与长度为j的串的最长公共子串
            //if char[i] == char[j]  dp[i][j] = dp[i-1][j-1] +1
            //if char[i] != char[j]  dp[i][j] = Max(dp[i-1][j],dp[i][j-1])

            int dp[][] = new int[char1.length + 1][char2.length + 1];
            for (int i = 1; i <= char1.length; i++) {
                for (int j = 1; j <= char2.length; j++) {
                    if (char1[i - 1] == char2[j - 1])
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    else
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }

            return dp[char1.length][char2.length];
        }

        //https://leetcode-cn.com/problems/longest-palindromic-subsequence/
        //516. 最长回文子序列      s = "bbbab"   4
        //dp的递推公式不是本质，本质是理解动态规划是带记事本的暴力循环
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            int[][] f = new int[n][n];
            for (int i = n - 1; i >= 0; i--) {
                f[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        f[i][j] = f[i + 1][j - 1] + 2;
                    } else {
                        f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                    }
                }
            }
            return f[0][n - 1];
        }
    }

}
