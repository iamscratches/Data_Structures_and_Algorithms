# 🔍 Binary Search - Efficient Element Search

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/binary-search/description/)

Given an array of integers `nums` sorted in ascending order, and an integer `target`, return the index of `target` in `nums` or `-1` if not found.

**Constraints:**
- 1 ≤ nums.length ≤ 10⁴
- -10⁴ ≤ nums[i], target ≤ 10⁴
- All elements in `nums` are unique
- `nums` is sorted in ascending order

**Example 1:**
```text
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums at index 4
```

**Example 2:**
```text
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums
```

---

## 🧠 Intuition
The solution uses the classic binary search algorithm:
1. **Divide and Conquer**: Repeatedly divides the search space in half
2. **Comparison**: At each step, compares the target with the middle element
3. **Narrowing Search**: Eliminates half of the remaining elements based on comparison

Key Insights:
- Works only on sorted arrays
- Each iteration reduces the search space by half
- Achieves O(log n) time complexity

---

## ⚙️ Approach
### **1️⃣ Initialize Pointers**
1. Set `low` to start of array (0)
2. Set `high` to end of array (nums.length - 1)

### **2️⃣ Binary Search Loop**
1. Calculate `mid` point
2. Compare `nums[mid]` with `target`:
   - If equal: return `mid`
   - If `nums[mid] < target`: search right half (`low = mid + 1`)
   - Else: search left half (`high = mid - 1`)

### **3️⃣ Termination**
- Loop ends when `low > high` (target not found)
- Return -1 if target not found

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents overflow
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return -1;
    }
}
```

Key Components:
1. **Pointer Initialization**: Sets search boundaries
2. **Mid Calculation**: Uses safe formula to prevent overflow
3. **Three-Way Comparison**: Handles equal, less-than, greater-than cases
4. **Termination Condition**: Ends when search space exhausted

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Halves search space each iteration |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [-1,0,3,5,9,12], target = 9`

**Execution Steps:**
1. Initial: low=0, high=5
2. Iteration 1: mid=2 (nums[2]=3) < 9 → low=3
3. Iteration 2: mid=4 (nums[4]=9) == 9 → return 4

**Result:** 4

---

## 💡 Key Features
- **Optimal Efficiency**: O(log n) time
- **Minimal Space**: No extra data structures
- **Overflow Protection**: Safe mid calculation
- **Clear Logic**: Easy to understand and implement

---

## 🚀 When to Use
- **Searching in sorted arrays**
- **When O(n) search is too slow**
- **As building block** for more complex algorithms
- **Problems requiring fast lookup**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for sorted arrays |
| Linear Search | O(n) | O(1) | Works on unsorted arrays |
| Hash Set | O(1) average | O(n) | Faster but more space |

## ⚠️ Edge Cases
- **Target at first/last index**: Works correctly
- **Target not present**: Returns -1
- **Single element array**: Handles properly
- **Duplicate elements**: Problem states all elements are unique

## 🛠 Variations
1. **Lower Bound Search**:
```java
// Find first occurrence of target
```

2. **Upper Bound Search**:
```java
// Find last occurrence of target
```

3. **Rotated Sorted Array**:
```java
// Modified binary search for rotated arrays
```

This solution demonstrates the fundamental binary search algorithm, providing optimal search performance for sorted arrays while being simple to implement and understand. The safe mid calculation ensures correctness even with large array indices.