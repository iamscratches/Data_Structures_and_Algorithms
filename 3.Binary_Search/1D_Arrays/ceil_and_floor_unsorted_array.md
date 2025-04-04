# 🔍 Ceil and Floor in an Array - Linear Search Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/ceil-the-floor2802/1)

Given an unsorted array `arr` of size `n` and an integer `x`, find both the floor and ceiling of `x` in the array. 
- Floor: Largest element ≤ `x`
- Ceiling: Smallest element ≥ `x`

**Constraints:**
- 1 ≤ n ≤ 10⁵
- 1 ≤ arr[i], x ≤ 10⁹

**Example 1:**
```text
Input: arr = [5, 6, 8, 9, 6, 5, 5, 6], x = 7
Output: [6, 8]
Explanation: Floor is 6, Ceiling is 8
```

**Example 2:**
```text
Input: arr = [1, 2, 8, 10, 10, 12, 19], x = 5
Output: [2, 8]
Explanation: Floor is 2, Ceiling is 8
```

---

## 🧠 Intuition
The solution uses a linear scan with these key insights:
1. **Direct Comparison**: Checks each element against `x`
2. **Dual Tracking**: Simultaneously tracks floor and ceiling candidates
3. **Early Termination**: Stops if exact match found (`floor = ceiling = x`)

Key Observations:
- Works efficiently for unsorted arrays
- Single pass through the array
- Handles edge cases like no floor/ceiling

---

## ⚙️ Approach
### **1️⃣ Initialize Trackers**
1. `floor = -1` (no floor found yet)
2. `ceil = Integer.MAX_VALUE` (no ceiling found yet)

### **2️⃣ Linear Scan**
For each element in array:
1. If element equals `x`:
   - Set both floor and ceiling to `x`
   - Terminate early
2. If element < `x`:
   - Update floor if element is larger than current floor
3. If element > `x`:
   - Update ceiling if element is smaller than current ceiling

### **3️⃣ Post-Processing**
- If no ceiling found (`ceil == Integer.MAX_VALUE`), set to -1

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int[] getFloorAndCeil(int x, int[] arr) {
        int floor = -1;
        int ceil = Integer.MAX_VALUE;
        
        for (int num : arr) {
            if (num == x) {
                // Exact match found
                floor = x;
                ceil = x;
                break;
            } else if (num < x) {
                // Update floor candidate
                floor = Math.max(floor, num);
            } else {
                // Update ceiling candidate
                ceil = Math.min(ceil, num);
            }
        }
        
        // Handle case where no ceiling exists
        if (ceil == Integer.MAX_VALUE) {
            ceil = -1;
        }
        
        return new int[]{floor, ceil};
    }
}
```

Key Components:
1. **Initialization**: Sets up floor and ceiling trackers
2. **Element Comparison**: Three-way comparison (equal, less, greater)
3. **Tracker Updates**: Maintains best candidates
4. **Edge Handling**: Deals with missing floor/ceiling

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through array |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `arr = [5,6,8,9,6,5,5,6], x = 7`

**Execution Steps:**
1. Initialize: floor=-1, ceil=MAX
2. Process 5: Update floor=5
3. Process 6: Update floor=6
4. Process 8: Update ceil=8
5. Process 9: ceil remains 8 (9 > 8)
6. (Continue through array)
7. **Result:** [6, 8]

---

## 💡 Key Features
- **Unsorted Array Handling**: Doesn't require sorting
- **Early Termination**: Optimizes for exact matches
- **Clear Logic**: Easy to understand implementation
- **Comprehensive**: Finds both floor and ceiling

---

## 🚀 When to Use
- **Unsorted array searches**
- **When both floor and ceiling are needed**
- **Problems requiring single pass solutions**
- **As building block** for range query problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Linear Scan (this) | O(n) | O(1) | Works on unsorted arrays |
| Binary Search | O(n log n) | O(1) | Requires sorted array |
| TreeSet | O(n log n) | O(n) | Java-specific, more overhead |

## ⚠️ Edge Cases
- **x smaller than all elements**: floor=-1
- **x larger than all elements**: ceil=-1
- **Exact match exists**: returns [x, x]
- **Empty array**: Constraint says n ≥ 1

## 🛠 Variations
1. **Sorted Array Version**:
```java
// Use binary search for O(log n) time
```

2. **Nearest Value**:
```java
// Find element closest to x
```

3. **Multiple Queries**:
```java
// Preprocess array for faster queries
```

This solution demonstrates an efficient linear scan approach to find both floor and ceiling values in an unsorted array, providing optimal O(n) performance while handling all edge cases. The implementation is straightforward and works without any preprocessing of the input array.