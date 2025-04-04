# 🔍 Find First and Last Position - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/)

Given a sorted array of integers `arr` and a target value `target`, return the starting and ending positions of `target` in `arr`. If `target` is not found, return `[-1, -1]`.

**Constraints:**
- 0 ≤ arr.length ≤ 10⁵
- -10⁹ ≤ arr[i], target ≤ 10⁹
- Array is sorted in non-decreasing order

**Example 1:**
```text
Input: arr = [5,7,7,8,8,10], target = 8
Output: [3,4]
Explanation: 8 appears at positions 3 and 4
```

**Example 2:**
```text
Input: arr = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Explanation: 6 doesn't appear in the array
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Two-Phase Search**: First finds any occurrence, then expands to find boundaries
2. **Modified Binary Searches**: Separate searches for first and last occurrences
3. **Early Termination**: Returns immediately if target not found

Key Observations:
- Binary search efficiently locates the target range
- First occurrence is where `arr[mid] == target` and either `mid == 0` or `arr[mid-1] < target`
- Last occurrence is where `arr[mid] == target` and either `mid == arr.length-1` or `arr[mid+1] > target`

---

## ⚙️ Approach
### **1️⃣ Initial Binary Search**
1. Standard binary search to find any target occurrence
2. If found, proceed to find boundaries

### **2️⃣ Find First Occurrence**
1. Search left half from initial found index
2. Adjust pointers to find left boundary

### **3️⃣ Find Last Occurrence**
1. Search right half from initial found index
2. Adjust pointers to find right boundary

### **4️⃣ Result Compilation**
- Returns `[first_occurrence, last_occurrence]`
- Returns `[-1, -1]` if target not found

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int[] searchRange(int[] arr, int target) {
        if (arr.length == 0) {
            return new int[]{-1, -1};
        }
        
        int left = 0, right = arr.length - 1;
        int[] result = new int[]{-1, -1};
        
        // Initial binary search to find any occurrence
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                // Find boundaries
                result[0] = findFirstIndex(arr, target, left, mid);
                result[1] = findLastIndex(arr, target, mid, right);
                return result;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    private int findFirstIndex(int[] arr, int target, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    private int findLastIndex(int[] arr, int target, int left, int right) {
        while (left < right) {
            int mid = left + (right - left + 1) / 2; // Ceiling division
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
```

Key Components:
1. **Initial Search**: Locates any target occurrence
2. **Boundary Searches**: Modified binary searches for first/last positions
3. **Result Handling**: Returns positions or [-1,-1]
4. **Edge Handling**: Empty array case handled immediately

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Three binary searches |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `arr = [5,7,7,8,8,10], target = 8`

**Execution Steps:**
1. Initial search finds 8 at index 3
2. Find first occurrence:
   - Searches [0,3] → finds index 3
   - Then adjusts to find first at index 3
3. Find last occurrence:
   - Searches [3,5] → finds index 4
4. **Result:** [3,4]

---

## 💡 Key Features
- **Optimal Efficiency**: O(log n) time
- **Accurate Positioning**: Precise boundary detection
- **Minimal Space**: No additional data structures
- **Clear Logic**: Well-separated search phases

---

## 🚀 When to Use
- **Finding element ranges in sorted arrays**
- **When both start and end positions are needed**
- **Problems requiring range queries**
- **As building block** for more complex search operations

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for sorted arrays |
| Linear Scan | O(n) | O(1) | Simple but inefficient |
| Two Separate Searches | O(log n) | O(1) | Alternative implementation |

## ⚠️ Edge Cases
- **Target not present**: Returns [-1,-1]
- **Single occurrence**: Returns [pos,pos]
- **All elements same**: Returns [0, arr.length-1] if matching target
- **Empty array**: Handled immediately

## 🛠 Variations
1. **Count Occurrences**:
```java
// Return count instead of positions
```

2. **Find Closest Elements**:
```java
// Find k elements closest to target
```

3. **Range Sum Query**:
```java
// Calculate sum between first and last positions
```

This solution demonstrates an efficient binary search adaptation to find the range of a target in sorted arrays, providing optimal O(log n) performance while accurately determining the target's boundaries. The implementation handles all edge cases and works within the given constraints.