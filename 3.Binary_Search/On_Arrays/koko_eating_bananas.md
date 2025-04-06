# 🍌 Koko Eating Bananas - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/koko-eating-bananas/description/)

Koko loves to eat bananas. There are `n` piles of bananas, the `i-th` pile has `piles[i]` bananas. The guards have gone and will come back in `h` hours.

Koko can decide her bananas-per-hour eating speed of `k`. Each hour, she chooses some pile of bananas and eats `k` bananas from that pile. If the pile has less than `k` bananas, she eats all of them and won't eat any more bananas during this hour.

Koko wants to finish eating all the bananas before the guards come back. Return the minimum integer `k` such that she can eat all the bananas within `h` hours.

**Constraints:**
- 1 ≤ piles.length ≤ 10⁴
- piles.length ≤ h ≤ 10⁹
- 1 ≤ piles[i] ≤ 10⁹

**Example 1:**
```text
Input: piles = [3,6,7,11], h = 8
Output: 4
Explanation:
- At k=4:
  - 3/4 → 1 hour
  - 6/4 → 2 hours
  - 7/4 → 2 hours
  - 11/4 → 3 hours
  - Total = 1+2+2+3 = 8 hours
```

**Example 2:**
```text
Input: piles = [30,11,23,4,20], h = 5
Output: 30
Explanation:
- At k=30:
  - 30/30 → 1 hour
  - 11/30 → 1 hour
  - 23/30 → 1 hour
  - 4/30 → 1 hour
  - 20/30 → 1 hour
  - Total = 1+1+1+1+1 = 5 hours
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Speed Range**: The minimum possible speed is 1, and the maximum is the largest pile (since eating faster than the largest pile doesn't help)
2. **Monotonicity**: If Koko can finish at speed `k`, she can definitely finish at any speed > `k`
3. **Binary Search**: We can efficiently search for the minimal `k` between 1 and max(piles)

Key Observations:
- The problem is about finding the minimal valid solution in a search space
- For each candidate speed, we can calculate the total hours needed
- The validity function (can finish in h hours) is monotonic

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize `low = 1`, `high = max(piles)`
2. Initialize `result` with the worst-case solution (max speed)

### **2️⃣ Binary Search Execution**
1. Calculate `mid` speed
2. Check if Koko can finish at this speed:
   - Calculate total hours needed for all piles at speed `mid`
   - Compare with available hours `h`
3. Adjust search space:
   - If possible, try smaller speeds (search left)
   - If not possible, try larger speeds (search right)

### **3️⃣ Optimal Solution**
- Track the minimal valid speed throughout the search
- Returns the smallest valid speed found

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int high = -1;
        for(int pile : piles){
            high = Math.max(high, pile);
        }
        int low = 1;
        int mid, result = high;
        
        while(high >= low){
            mid = low + (high - low)/2;
            
            if(isPossible(piles, mid, h)){
                result = mid;  // Update result to current valid speed
                high = mid - 1;  // Try to find a smaller valid speed
            }
            else{
                low = mid + 1;  // Need a faster speed
            }
        }
        return result;
    }
    
    private boolean isPossible(int[] piles, int k, int h){
        long count = 0;  // Use long to prevent integer overflow
        for(int pile : piles){
            count += (pile + k - 1) / k;  // Ceiling division
            if(count > h){
                return false;
            }
        }
        return true;
    }
}
```

Key Components:
1. **Initialization**: Finds maximum pile size to set upper bound
2. **Binary Search**: Efficiently narrows down speed range
3. **Feasibility Check**: Calculates total hours needed with ceiling division
4. **Result Tracking**: Keeps track of minimal valid speed
5. **Overflow Protection**: Uses long for hour counting

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n log m) | n = piles.length, m = max(piles) |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `piles = [3,6,7,11], h = 8`

**Execution Steps:**
1. Initialize: low=1, high=11, result=11
2. mid=6 → hours=1+1+2+2=6 ≤ 8 → result=6, high=5
3. mid=3 → hours=1+2+3+4=10 > 8 → low=4
4. mid=4 → hours=1+2+2+3=8 ≤ 8 → result=4, high=3
5. Exit loop (high < low)

**Result:** 4

---

## 💡 Key Features
- **Binary Search on Answer**: Searches for minimal valid speed
- **Efficient Validation**: Quick feasibility check for each candidate speed
- **Ceiling Division**: Properly calculates hours per pile
- **Optimal Solution**: Guaranteed to find minimal valid speed

---

## 🚀 When to Use
- **Minimization problems with monotonic validity functions**
- **When brute force search would be too slow**
- **Problems where you can verify solutions faster than finding them**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(n log m) | O(1) | Optimal for large inputs |
| Linear Search | O(n × m) | O(1) | Simple but impractical |
| Mathematical Approach | Depends on input | O(1) | Not always applicable |

## ⚠️ Edge Cases
- **Single pile**: Returns pile.size/h (ceiling)
- **Equal piles**: Returns ceiling(pile/h)
- **Large h**: Returns minimum speed (1)
- **Large piles**: Handles with binary search efficiency

## 🛠 Variations
1. **Variable Eating Rates**:
```java
// If different piles have different eating rate constraints
```

2. **Time-Varying Guards**:
```java
// If guard return time varies based on some function
```

3. **Multiple Kokos**:
```java
// If multiple eaters share the work
```

This solution demonstrates an efficient binary search adaptation to find the minimal eating speed, providing optimal O(n log m) performance while handling all edge cases. The implementation maintains O(1) space complexity and works within the problem constraints.