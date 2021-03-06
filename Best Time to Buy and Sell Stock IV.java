You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), 
profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

  
  
    /**
 * dp[i, j] represents the max profit up until prices[j] using at most i transactions. 
 * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
 *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
 * dp[0, j] = 0; 0 transactions makes 0 profit
 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
 */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 0){
            return 0;
        }
        int len = prices.length;
        
        if (k >= len / 2){
            int max = 0;
            for (int i = 1; i < prices.length; i++){
                if (prices[i] > prices[i - 1]){
                    max += prices[i] - prices[i - 1];
                }
            }
            return max;
        }
       int[][] result = new int[k + 1][len];
        
        for (int i = 1; i <= k; i++){
            int localMax = - prices[0];
            for (int j = 1; j < len; j++){
                result[i][j] = Math.max(result[i][j - 1], prices[j] + localMax);
                localMax = Math.max(localMax, result[i - 1][j] - prices[j]);
            }
        }
	return result[k][len - 1];  
  
  
