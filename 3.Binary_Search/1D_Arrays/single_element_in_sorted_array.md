# 🔍 Single Element in Sorted Array - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/single-element-in-a-sorted-array/description/)

Given a sorted array where every element appears exactly twice except for one element which appears exactly once, find that single element.

**Constraints:**
- 1 ≤ nums.length ≤ 10⁵
- 0 ≤ nums[i] ≤ 10⁵
- Array is sorted in non-decreasing order

**Example 1:**
```text
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
```

**Example 2:**
```text
Input: nums = [3,3,7,7,10,11,11]
Output: 10
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Pair Pattern**: Before the single element, pairs appear at even-odd indices
2. **Pattern Flip**: After the single element, pairs appear at odd-even indices
3. **Mid Check**: The single element breaks the normal pairing pattern

Key Observations:
- The single element will always be at an even index in a 0-based array
- Binary search can exploit this pattern to find the element in O(log n) time

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize `start = 0`, `end = nums.length - 1`
2. Handle edge case for single-element array

### **2️⃣ Modified Binary Search**
1. Calculate `mid`
2. Check pairing with next or previous element:
   - If paired with next element:
     - Correct pairing before single element: search right
     - Incorrect pairing: search left
   - If paired with previous element:
     - Correct pairing after single element: search left
     - Incorrect pairing: search right
3. If no pairing found, return `nums[mid]`

### **3️⃣ Early Termination**
- Returns immediately when single element is found
- Terminates when search space is exhausted

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        int start = 0, end = nums.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            // Check if mid is the single element
            if ((mid == 0 || nums[mid] != nums[mid - 1]) && 
                (mid == nums.length - 1 || nums[mid] != nums[mid + 1])) {
                return nums[mid];
            }
            
            // Determine search direction based on pairing
            if (mid % 2 == 0) {
                if (mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                    start = mid + 2;
                } else {
                    end = mid - 1;
                }
            } else {
                if (mid > 0 && nums[mid] == nums[mid - 1]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        
        return -1; // Should never reach here for valid input
    }
}
```

Key Components:
1. **Edge Handling**: Direct return for single-element array
2. **Mid Check**: Verifies if mid is the single element
3. **Pair Analysis**: Uses index parity to determine search direction
4. **Termination**: Returns when single element is found

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Binary search halves problem space |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [1,1,2,3,3,4,4,8,8]`

**Execution Steps:**
1. Initial: start=0, end=8
2. mid=4 (3) → paired with nums[5] (4) → pattern broken → end=3
3. mid=1 (1) → paired with nums[0] (1) → correct pairing → start=2
4. mid=2 (2) → no pairing → return 2

**Result:** 2

---

## 💡 Key Features
- **Pattern Recognition**: Leverages pairing pattern around single element
- **Optimal Efficiency**: O(log n) time complexity
- **Index Parity**: Uses even/odd indices to guide search
- **Early Termination**: Returns immediately upon finding the element

---

## 🚀 When to Use
- **Finding unique elements in sorted arrays**
- **When O(n) linear search is too slow**
- **Problems with pairing patterns**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for large arrays |
| Linear Search | O(n) | O(1) | Simple but inefficient |
| XOR Operation | O(n) | O(1) | Works on unsorted arrays |

## ⚠️ Edge Cases
- **Single-element array**: Returns the element
- **Element at start/end**: Handled correctly
- **Large input size**: Efficient performance guaranteed
- **All pairs except last**: Correctly identifies last element

## 🛠 Variations
1. **Two Unique Elements**:
```java
// Find two elements appearing once in sorted array
```

2. **Unsorted Array Version**:
```java
// Use XOR operation for O(n) time
```

3. **k Occurrences**:
```java
// Find element appearing k times when others appear m times
```

This solution demonstrates an efficient binary search adaptation to find the single element in a sorted array of pairs, providing optimal O(log n) performance while handling all edge cases. The implementation maintains O(1) space complexity and works within the problem constraints.