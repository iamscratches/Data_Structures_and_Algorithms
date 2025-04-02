# 🔄 Count Inversions in an Array - Merge Sort Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1)

Given an array of N integers, count the number of inversions where an inversion is defined as a pair of indices (i, j) such that i < j and arr[i] > arr[j].

**Constraints:**
- 1 ≤ N ≤ 5×10⁵
- 1 ≤ arr[i] ≤ 10¹⁸

**Example 1:**
```text
Input: arr = [2, 4, 1, 3, 5]
Output: 3
Explanation: The inversion pairs are (2,1), (4,1), and (4,3)
```

**Example 2:**
```text
Input: arr = [2, 3, 4, 5, 6]
Output: 0
Explanation: The array is already sorted
```

---

## 🧠 Intuition
The solution uses a modified Merge Sort:
1. **Divide and Conquer**: Splits the array into halves
2. **Merge with Count**: During merging, counts inversions when elements from right half are smaller
3. **Recursive Count**: Combines counts from left, right, and merge operations

Key Insights:
- Inversions can be counted during the merge process
- When an element from the right half is smaller, it forms inversions with all remaining left elements
- Merge Sort's O(n log n) complexity is optimal for this problem

---

## ⚙️ Approach
### **1️⃣ Modified Merge Sort**
1. **Divide Phase**:
   - Recursively split the array into halves
   - Count inversions in left and right halves

2. **Merge Phase**:
   - Compare elements from both halves
   - When right element is smaller, it forms inversions with all remaining left elements
   - Merge the sorted halves while counting these inversions

### **2️⃣ Inversion Counting**
- For each element in right half that's smaller than left half elements:
  - Number of inversions += remaining elements in left half
- Accumulate counts from all recursive calls

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    // Main function to count inversions
    static int inversionCount(int arr[]) {
        return divide(arr, 0, arr.length-1);
    }
    
    // Recursive divide and merge function
    private static int divide(int[] arr, int start, int end) {
        if (start >= end) {
            return 0;
        }
        
        int mid = start + (end - start)/2;
        int count = 0;
        
        // Count inversions in left and right halves
        count += divide(arr, start, mid);
        count += divide(arr, mid+1, end);
        
        // Merge and count split inversions
        int i = start, j = mid+1, k = 0;
        int[] sorted = new int[end - start + 1];
        
        while (i <= mid && j <= end) {
            if (arr[i] > arr[j]) {
                // All remaining left elements form inversions
                count += (mid - i + 1);
                sorted[k++] = arr[j++];
            } else {
                sorted[k++] = arr[i++];
            }
        }
        
        // Copy remaining elements
        while (i <= mid) {
            sorted[k++] = arr[i++];
        }
        while (j <= end) {
            sorted[k++] = arr[j++];
        }
        
        // Copy back to original array
        System.arraycopy(sorted, 0, arr, start, sorted.length);
        
        return count;
    }
}
```

Key Components:
1. **Recursive Division**: Splits array into halves
2. **Inversion Counting**: During merge phase
3. **Efficient Merging**: Uses temporary array
4. **In-Place Sorting**: Maintains sorted subarrays

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n log n) | Same as merge sort |
| **Space**       | O(n)       | Temporary array for merging |

---

## 📊 Example Walkthrough

**Input:** `[2, 4, 1, 3, 5]`

**Execution Steps:**
1. **Divide**:
   - Split into [2,4,1] and [3,5]
   - Further split [2,4,1] into [2] and [4,1]
2. **Merge and Count**:
   - Merge [4] and [1]: count +=1 (inversion (4,1))
   - Merge [2] and [1,4]: count +=1 (inversion (2,1))
   - Merge [1,2,4] and [3,5]: count +=1 (inversion (4,3))
3. **Total Inversions**: 3

---

## 💡 Key Features
- **Optimal Complexity**: O(n log n) time
- **In-Place Sorting**: Modifies original array
- **Accurate Counting**: Handles all inversion cases
- **Stable Algorithm**: Maintains relative order of equal elements

---

## 🚀 When to Use
- **Counting inversions in arrays**
- **Measuring array sortedness**
- **Problems requiring comparison counts**
- **As building block** for more complex algorithms

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Merge Sort (this) | O(n log n) | O(n) | Optimal for large n |
| Brute Force | O(n²) | O(1) | Simple but inefficient |
| Binary Indexed Tree | O(n log n) | O(n) | Alternative for online queries |

## ⚠️ Edge Cases
- **Already sorted array**: Returns 0
- **Reverse sorted array**: Maximum inversions (n(n-1)/2)
- **Single element**: Returns 0
- **Large arrays**: Handles within constraints

## 🛠 Variations
1. **Count Inversions in Linked List**:
```java
// Adapt merge sort for linked lists
```

2. **Find Inversion Pairs**:
```java
// Return actual pairs instead of count
```

3. **Online Inversion Count**:
```java
// Maintain count while inserting elements
```

This solution demonstrates an efficient divide-and-conquer approach to count inversions, leveraging merge sort's natural properties to achieve optimal time complexity while accurately tracking inversion counts during the merge process.