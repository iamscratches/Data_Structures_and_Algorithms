# 🚀 Best Time to Buy and Sell Stock - One Pass Algorithm

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/)

Given an array `prices` where `prices[i]` is the price of a given stock on day `i`, maximize your profit by choosing a single day to buy and a different day in the future to sell. Return the maximum profit possible. If no profit can be achieved, return 0.

**Constraints:**
- 1 ≤ prices.length ≤ 10⁵
- 0 ≤ prices[i] ≤ 10⁴

**Example 1:**
```text
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price=1) and sell on day 5 (price=6), profit=6-1=5
```

**Example 2:**
```text
Input: [7,6,4,3,1]
Output: 0
Explanation: No transactions give positive profit
```

---

## 🧠 Intuition
The optimal solution uses:
1. **Single pass** through the price array
2. **Track minimum price** seen so far
3. **Calculate potential profit** at each day
4. **Update maximum profit** found

Key Insights:
- Maximum profit comes from buying at the lowest point before selling
- Only need to compare with the minimum price seen so far
- Negative profits are automatically handled by initializing maxProfit to 0

---

## ⚙️ Approach
### **1️⃣ One Pass Algorithm**
1. Initialize:
   - `minPrice` = first day's price
   - `maxProfit` = 0
2. For each subsequent day:
   - Calculate potential profit (`currentPrice - minPrice`)
   - Update `maxProfit` if this profit is greater
   - Update `minPrice` if current price is lower
3. Return `maxProfit`

### **2️⃣ Why It Works**
- Tracks the lowest buying opportunity
- Calculates all possible selling opportunities after that point
- Efficiently finds maximum difference in O(n) time

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            // Calculate potential profit if sold today
            int potentialProfit = currentPrice - minPrice;
            // Update maximum profit found
            maxProfit = Math.max(maxProfit, potentialProfit);
            // Update minimum price seen so far
            minPrice = Math.min(minPrice, currentPrice);
        }
        
        return maxProfit;
    }
}
```

Key Components:
1. **Initialization**: Sets up tracking variables
2. **Profit Calculation**: Potential profit at each day
3. **Max Update**: Tracks highest profit found
4. **Min Update**: Maintains lowest buy price

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through prices |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `[7,1,5,3,6,4]`

**Execution Steps:**
1. Initialize: min=7, max=0
2. Day1(1): profit=-6 → max=0, min=1
3. Day2(5): profit=4 → max=4, min=1
4. Day3(3): profit=2 → max=4, min=1
5. Day4(6): profit=5 → max=5, min=1
6. Day5(4): profit=3 → max=5, min=1

**Output:** `5` (buy at 1, sell at 6)

---

## 💡 Key Features
- **Efficiency**: Optimal O(n) time
- **Simplicity**: Easy to understand and implement
- **Handles Edge Cases**: No profit scenario automatically returns 0
- **Space Optimization**: Uses only two variables

---

## 🚀 When to Use
- **Single buy/sell stock problems**
- **Finding maximum difference** in sequences
- **Real-time price analysis** applications
- **As building block** for more complex trading algorithms

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| One Pass (this) | O(n)            | O(1)             | Optimal    |
| Brute Force     | O(n²)           | O(1)             | None       |
| Dynamic Prog.   | O(n)            | O(n)             | Overkill   |

## ⚠️ Edge Cases
- **All decreasing prices**: Returns 0
- **Single price**: Returns 0 (can't sell)
- **All same prices**: Returns 0
- **Large price swings**: Handles within constraints

## 🛠 Variations
1. **Return Buy/Sell Days**:
```java
int buyDay = 0, sellDay = 0;
if (currentProfit > maxProfit) {
    sellDay = i;
    buyDay = minPriceDay;
}
```

2. **Multiple Transactions** (Problem Variation):
```java
// Would require different approach
// Sum all increasing consecutive differences
```

3. **With Transaction Fee**:
```java
// Adjust profit calculation with fee
maxProfit = Math.max(maxProfit, price - minPrice - fee);
```

This solution demonstrates how careful tracking of minimum values can efficiently solve maximum difference problems, making it ideal for financial applications and sequence analysis.