# 🔀 Merge Sorted Array - Efficient In-Place Merging

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/merge-sorted-array/description/)

Given two sorted integer arrays `nums1` and `nums2`, merge them into a single sorted array stored in `nums1`. 

**Key Details:**
- `nums1` has length `m + n` with `m` elements initialized
- `nums2` has length `n`
- Merge must be in-place without returning a new array

**Constraints:**
- nums1.length == m + n
- nums2.length == n
- 0 ≤ m, n ≤ 200
- 1 ≤ m + n ≤ 200
- -10⁹ ≤ nums1[i], nums2[j] ≤ 10⁹

**Example 1:**
```text
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
```

**Example 2:**
```text
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
```

---

## 🧠 Intuition
The optimal solution uses:
1. **Backward Merging**: Starts from the end of both arrays
2. **Three Pointers**: Tracks positions in nums1, nums2, and merged array
3. **In-Place Operation**: Utilizes nums1's extra space efficiently

Key Insights:
- Merging from the end prevents overwriting unprocessed elements
- No extra space needed by leveraging nums1's buffer
- After nums1 elements are processed, remaining nums2 elements can be copied directly

---

## ⚙️ Approach
### **1️⃣ Three-Pointer Technique**
1. Initialize pointers:
   - `i` at last element of nums1 (m-1)
   - `j` at last element of nums2 (n-1)
   - `k` at end of merged array (m+n-1)

2. While both arrays have elements:
   - Place larger element at `nums1[k]`
   - Move pointers backward accordingly

3. Handle remaining nums2 elements:
   - Directly copy if nums1 elements are exhausted

### **2️⃣ Why Backward?**
- Prevents overwriting nums1 elements before they're processed
- Naturally fills the array from largest to smallest
- Eliminates need for temporary storage

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointers
        int i = m - 1;  // Last element of nums1
        int j = n - 1;  // Last element of nums2
        int k = m + n - 1;  // End of merged array
        
        // Merge while both arrays have elements
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        
        // Copy remaining nums2 elements if any
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
```

Key Components:
1. **Pointer Initialization**: Sets up correct starting positions
2. **Comparative Merging**: Always places larger element first
3. **Remaining Elements Handling**: Efficient final copy
4. **In-Place Operation**: Modifies nums1 directly

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(m + n)   | Single pass through both arrays |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3`

**Execution Steps:**
1. Initialize: i=2, j=2, k=5
2. Compare nums1[2](3) vs nums2[2](6):
   - Place 6 at nums1[5] → [1,2,3,0,0,6]
   - Decrement j and k
3. Compare nums1[2](3) vs nums2[1](5):
   - Place 5 at nums1[4] → [1,2,3,0,5,6]
   - Decrement j and k
4. Compare nums1[2](3) vs nums2[0](2):
   - Place 3 at nums1[3] → [1,2,3,3,5,6]
   - Decrement i and k
5. Compare nums1[1](2) vs nums2[0](2):
   - Place 2 at nums1[2] → [1,2,2,3,5,6]
   - Decrement j and k
6. Exit first loop (j < 0)
7. Final array: `[1,2,2,3,5,6]`

---

## 💡 Key Features
- **Space Efficient**: Uses existing array space
- **Single Pass**: Optimal O(m+n) time complexity
- **No Data Loss**: Backward merge prevents overwrites
- **Clean Termination**: Handles remaining elements elegantly

---

## 🚀 When to Use
- **Merging sorted arrays in-place**
- **Space-constrained environments**
- **As building block** for more complex merge operations
- **When input modification is allowed**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Backward Merge (this) | O(m+n) | O(1) | Most space efficient |
| Extra Array | O(m+n) | O(m+n) | Simpler but more space |
| System.arraycopy | O(m+n) | O(m) | Java-specific optimization |

## ⚠️ Edge Cases
- **Empty nums2**: Returns nums1 unchanged
- **Empty nums1**: Copies entire nums2
- **All nums1 elements smaller**: Appends nums2
- **All nums2 elements smaller**: Shifts nums1 right

## 🛠 Variations
1. **Merge K Sorted Arrays**:
```java
// Extend using priority queue
```

2. **Merge With Limited Buffer**:
```java
// When additional space is constrained
```

3. **Stable Merge Sort**:
```java
// Preserve original order of equal elements
```

This solution demonstrates an optimal pattern for in-place array merging, combining efficiency with elegant pointer manipulation. The backward merging strategy is particularly useful for space-constrained scenarios.