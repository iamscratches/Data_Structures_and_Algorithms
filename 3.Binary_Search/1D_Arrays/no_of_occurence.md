# 🔢 Count Occurrences in Sorted Array - Binary Search Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/number-of-occurrence2259/1)

Given a sorted array `arr` of size `n` and an integer `target`, find the count of occurrences of `target` in the array.

**Constraints:**
- 1 ≤ n ≤ 10⁵
- 1 ≤ arr[i], target ≤ 10⁸
- Array is sorted in non-decreasing order

**Example 1:**
```text
Input: arr = [1,1,2,2,2,2,3], target = 2
Output: 4
Explanation: 2 appears 4 times in the array
```

**Example 2:**
```text
Input: arr = [2,4,6,8,10], target = 5
Output: 0
Explanation: 5 doesn't appear in the array
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Two-Phase Search**: First finds any occurrence, then finds first and last occurrences
2. **Boundary Expansion**: From initial found index, expands to find boundaries
3. **Count Calculation**: Returns difference between boundaries + 1

Key Observations:
- Binary search efficiently locates the target range
- Separate searches for first and last occurrences ensure accuracy
- O(log n) time complexity is optimal for large sorted arrays

---

## ⚙️ Approach
### **1️⃣ Initial Binary Search**
1. Standard binary search to find any target occurrence
2. If found, proceed to find boundaries

### **2️⃣ Find First Occurrence**
1. Search left half from initial found index
2. Adjust end pointer to find left boundary

### **3️⃣ Find Last Occurrence**
1. Search right half from initial found index
2. Adjust start pointer to find right boundary

### **4️⃣ Count Calculation**
- Returns `(last_occurrence - first_occurrence) + 1`
- Returns 0 if target not found

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    int countFreq(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        // Initial binary search to find any occurrence
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                // Find boundaries
                int first = findFirstIndex(arr, target, 0, mid);
                int last = findLastIndex(arr, target, mid, arr.length - 1);
                return (last - first) + 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
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
3. **Count Formula**: Simple arithmetic to get total count
4. **Edge Handling**: Returns 0 for non-existent targets

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Three binary searches |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `arr = [1,1,2,2,2,2,3], target = 2`

**Execution Steps:**
1. Initial search finds 2 at index 3
2. Find first occurrence:
   - Searches [0,3] → finds index 2
3. Find last occurrence:
   - Searches [3,6] → finds index 5
4. Count calculation: (5 - 2) + 1 = 4

**Result:** 4

---

## 💡 Key Features
- **Optimal Efficiency**: O(log n) time
- **Accurate Counting**: Precise boundary detection
- **Minimal Space**: No additional data structures
- **Clear Logic**: Well-separated search phases

---

## 🚀 When to Use
- **Counting occurrences in sorted arrays**
- **When frequency matters more than positions**
- **Problems requiring range queries**
- **As building block** for statistical calculations

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for sorted arrays |
| Linear Scan | O(n) | O(1) | Simple but inefficient |
| Hash Map | O(n) | O(n) | Counts all elements |

## ⚠️ Edge Cases
- **Target not present**: Returns 0
- **Single occurrence**: Returns 1
- **All elements same**: Returns array length if matching target
- **Target at boundaries**: Handles first/last positions correctly

## 🛠 Variations
1. **Range Frequency Query**:
```java
// Count elements between [a,b]
```

2. **Most Frequent Element**:
```java
// Find element with max occurrences
```

3. **k-th Occurrence**:
```java
// Find position of k-th occurrence
```

This solution demonstrates an efficient binary search adaptation to count target occurrences in sorted arrays, providing optimal O(log n) performance while accurately determining the target's range. The implementation handles all edge cases and works within the given constraints.