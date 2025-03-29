# 🚀 Dutch National Flag Algorithm - Sort Colors

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/sort-colors/description/)

Given an array `nums` with `n` objects colored red (0), white (1), or blue (2), sort them in-place so that objects of the same color are adjacent.

**Constraints:**
- `n == nums.length`
- `1 <= n <= 300`
- `nums[i]` is either 0, 1, or 2

**Example:**
```text
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```

---

## 🧠 Intuition
The Dutch National Flag algorithm:
1. Uses three pointers to partition the array into three sections:
   - `0`s (red) at the beginning
   - `1`s (white) in the middle
   - `2`s (blue) at the end
2. Maintains invariants during sorting:
   - All elements before `i` are `0`s
   - All elements after `j` are `2`s
   - Elements between `i` and `k` are `1`s
   - Elements after `k` are unprocessed

---

## ⚙️ Approach
### **Three Pointer Technique**
1. **Initialization**:
   - `i = 0` (tracks right boundary of 0s)
   - `j = nums.length - 1` (tracks left boundary of 2s)
   - `k = 0` (current element pointer)

2. **Processing**:
   - When `nums[k] == 0`: Swap with `nums[i]` and increment both `i` and `k`
   - When `nums[k] == 2`: Swap with `nums[j]` and decrement `j` (don't increment `k`)
   - When `nums[k] == 1`: Just increment `k`

3. **Termination**:
   - Algorithm completes when `k` crosses `j` (`k > j`)

---

## 📐 Mathematical Derivation
### **1. Invariant Maintenance**
- After each iteration:
  - `[0..i-1]` contains only 0s
  - `[i..k-1]` contains only 1s
  - `[j+1..end]` contains only 2s
  - `[k..j]` contains unprocessed elements

### **2. Time Complexity**
- Single pass through the array: O(n)
- Each element is processed exactly once
- Constant time operations per element

### **3. Space Complexity**
- In-place sorting: O(1) additional space
- Only three pointers used

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public void sortColors(int[] arr) {
        int i = 0;          // Tracks right boundary of 0s
        int j = arr.length - 1; // Tracks left boundary of 2s
        int k = 0;          // Current element pointer
        
        while(k <= j) {
            if(arr[k] == 0 && k != i) {
                // Swap 0 to the left partition
                int temp = arr[k];
                arr[k] = arr[i];
                arr[i++] = temp;
            }
            else if(arr[k] == 2 && k != j) {
                // Swap 2 to the right partition
                int temp = arr[k];
                arr[k] = arr[j];
                arr[j--] = temp;
            }
            else {
                // Move past 1s
                k++;
            }
        }
    }
}
```

Key Components:
1. **Pointer Initialization**: Sets up three boundary pointers
2. **Swapping Logic**:
   - 0s are moved to the left (i pointer)
   - 2s are moved to the right (j pointer)
3. **Middle Section**: 1s remain in place as k advances
4. **Termination Condition**: Process until k > j

---

## ⏳ Complexity Analysis
### **Time Complexity: O(n)**
- Single pass through the array
- Each element processed exactly once

### **Space Complexity: O(1)**
- Constant extra space (only three pointers)
- In-place modification of input array

---

## 📊 Example Walkthrough

**Input:** `[2,0,2,1,1,0]`

**Execution Steps:**
1. Initial: [2,0,2,1,1,0], i=0, j=5, k=0
2. Swap arr[0] (2) with arr[5] (0): [0,0,2,1,1,2], j=4
3. k=0: arr[0]=0 → swap with arr[i=0] (no change), i=1, k=1
4. k=1: arr[1]=0 → swap with arr[i=1], i=2, k=2
5. k=2: arr[2]=2 → swap with arr[j=4]: [0,0,1,1,2,2], j=3
6. k=2: arr[2]=1 → k=3
7. k=3: arr[3]=1 → k=4
8. Terminate (k=4 > j=3)

**Output:** `[0,0,1,1,2,2]`

---

## 💡 Key Features
- **One-pass algorithm**: Extremely efficient
- **In-place sorting**: No additional memory needed
- **Maintains relative order**: For elements with same value (stable for 0s and 2s)
- **Generalizable**: Can be adapted for 3-way partitioning problems

---

## 🚀 When to Use
- **Sorting problems** with limited distinct values
- **Partitioning problems** with three categories
- **Memory-constrained** environments
- **Real-time systems** requiring O(n) sorting

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Stable Sort |
|------------------|-----------------|------------------|-------------|
| Dutch Flag (this)| O(n)            | O(1)             | Partially   |
| Counting Sort    | O(n)            | O(k)             | Yes         |
| QuickSort        | O(n log n)      | O(log n)         | No          |
| Brute Force      | O(n²)           | O(1)             | Depends     |

## ⚠️ Edge Cases
- **All elements same**: Handled efficiently
- **Empty array**: Returns immediately
- **Single element array**: No sorting needed
- **Already sorted**: Performs minimal operations

## 🛠 Variations
1. **Four-way Partitioning**:
```java
// Can be extended for 4 categories with additional pointers
```

2. **Generic Implementation**:
```java
void threeWayPartition(int[] nums, int lowVal, int highVal) {
    // Similar logic but with configurable ranges
}
```

3. **Stable Version**:
- Requires additional O(n) space
- Maintains relative order of all elements

This implementation represents the optimal solution for the sort colors problem, demonstrating elegant pointer manipulation for efficient in-place sorting.