# 🌹 Minimum Number of Days to Make M Bouquets - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/)

You are given an integer array `bloomDay` where `bloomDay[i]` is the day when the `i-th` flower will bloom. You are also given integers `m` (number of bouquets needed) and `k` (number of adjacent flowers needed per bouquet).

Return the minimum number of days you need to wait to be able to make `m` bouquets from the garden. If it's impossible, return -1.

**Constraints:**
- `bloomDay.length == n`
- `1 <= n <= 10^5`
- `1 <= bloomDay[i] <= 10^9`
- `1 <= m <= 10^6`
- `1 <= k <= n`

**Example 1:**
```text
Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
Output: 3
Explanation: 
Day 1: [x, _, _, _, _] → 1 bouquet
Day 2: [x, _, _, _, x] → 2 bouquets
Day 3: [x, _, x, _, x] → 3 bouquets
```

**Example 2:**
```text
Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
Output: 12
Explanation: 
Need 2 bouquets of 3 adjacent flowers each
On day 12: [x,x,x,x,x,x,x] → can make 2 bouquets
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Day Range**: The minimum possible day is 1, maximum is the latest bloom day
2. **Monotonicity**: If bouquets can be made on day `d`, they can definitely be made on any day > `d`
3. **Binary Search**: We can efficiently search for the minimal day between 1 and max(bloomDay)

Key Observations:
- The problem is about finding the earliest day when enough adjacent flowers have bloomed
- For each candidate day, we can check if enough bouquets can be formed
- The validity function (can make m bouquets) is monotonic

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. First check if it's possible to make m bouquets (m*k ≤ total flowers)
2. Initialize `low = 1`, `high = max(bloomDay)`
3. Initialize `result` with the worst-case solution (latest bloom day)

### **2️⃣ Binary Search Execution**
1. Calculate `mid` day
2. Check if m bouquets can be made by `mid` day:
   - Count adjacent bloomed flowers in a sliding window
   - Count how many bouquets can be formed
3. Adjust search space:
   - If possible, try earlier days (search left)
   - If not possible, try later days (search right)

### **3️⃣ Optimal Solution**
- Track the minimal valid day throughout the search
- Returns the smallest valid day found or -1 if impossible

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        // Early check for impossibility
        if ((long)m * k > (long)len) {
            return -1;
        }
        
        int low = 1, high = 0;
        for (int day : bloomDay) {
            high = Math.max(high, day);
        }
        int res = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canMakeBouquets(bloomDay, mid, m, k)) {
                res = mid;
                high = mid - 1; // Try to find a smaller day
            } else {
                low = mid + 1; // Need more days
            }
        }
        return res;
    }
    
    private boolean canMakeBouquets(int[] days, int currentDay, int requiredBouquets, int flowersPerBouquet) {
        int bouquets = 0;
        int adjacentFlowers = 0;
        
        for (int day : days) {
            if (day <= currentDay) {
                adjacentFlowers++;
                if (adjacentFlowers == flowersPerBouquet) {
                    bouquets++;
                    adjacentFlowers = 0;
                    if (bouquets >= requiredBouquets) {
                        return true;
                    }
                }
            } else {
                adjacentFlowers = 0;
            }
        }
        return bouquets >= requiredBouquets;
    }
}
```

Key Improvements:
1. **Early Impossibility Check**: Verifies if m*k > total flowers upfront
2. **Optimized Bouquet Counting**: Single pass through array with O(1) space
3. **Early Termination**: Stops counting when enough bouquets are found
4. **Sliding Window**: Efficiently counts adjacent bloomed flowers

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n log d) | n = bloomDay.length, d = max(bloomDay) |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `bloomDay = [1,10,3,10,2], m = 3, k = 1`

**Execution Steps:**
1. Check: 3*1 ≤ 5 → possible
2. Initialize: low=1, high=10, res=10
3. mid=5 → can make 3 bouquets? Yes (days 1,3,2) → res=5, high=4
4. mid=2 → can make 3 bouquets? No (only days 1,2) → low=3
5. mid=3 → can make 3 bouquets? Yes (days 1,3,2) → res=3, high=2
6. Exit loop (low > high)

**Result:** 3

---

## 💡 Key Features
- **Binary Search on Answer**: Searches for minimal valid day
- **Efficient Validation**: Single-pass bouquet counting
- **Early Checks**: Handles impossible cases immediately
- **Optimal Solution**: Guaranteed to find minimal valid day

---

## 🚀 When to Use
- **Minimization problems with monotonic validity functions**
- **When you need to find the earliest time something becomes possible**
- **Problems involving sequential/adjacent elements**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(n log d) | O(1) | Optimal for large inputs |
| Linear Search | O(n × d) | O(1) | Simple but impractical |
| Priority Queue | O(n log n) | O(n) | More complex, no advantage |

## ⚠️ Edge Cases
- **Not enough flowers**: Returns -1 immediately
- **Single bouquet**: Just need k adjacent flowers
- **All flowers bloom same day**: Returns that day
- **Large m/k values**: Handled by early check

## 🛠 Variations
1. **Non-Adjacent Flowers**:
```java
// If bouquets can use any flowers, not just adjacent
```

2. **Different Bouquet Sizes**:
```java
// If each bouquet requires different number of flowers
```

3. **Multiple Flower Types**:
```java
// If different flower types have different bloom requirements
```

This solution demonstrates an efficient binary search adaptation to find the minimal days needed for bouquet preparation, providing optimal O(n log d) performance while handling all edge cases. The implementation maintains O(1) space complexity and works within the problem constraints.