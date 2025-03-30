# 🚀 Maximum Sum in Subarrays - Simple Yet Effective Approach
## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/0)

Given an array `arr[]` with 0-based indexing, for any two indices `i` and `j` (where `i < j`):
1. Consider the subarray `arr[i...j]`
2. Find the smallest and second smallest numbers in this subarray
3. Their sum is the "score" for this subarray
4. Return the maximum possible score across all subarrays

**Constraints:**
- 2 ≤ N ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁹

**Example 1:**
```text
Input: [1, 4, 3, 2, 5]
Output: 5
Explanation: 
Subarray [1,4,3,2] has smallest=1 and second smallest=2 → score=3
Subarray [4,3,2,5] has smallest=2 and second smallest=3 → score=5 (maximum)
```

**Example 2:**
```text
Input: [6, 5, 4, 3, 2]
Output: 7
Explanation: 
Subarray [6,5,4,3] → 3+4=7
Subarray [5,4,3,2] → 2+3=5
Maximum score is 7
```

---

## 🧠 Intuition
The problem requires us to find the maximum sum of two smallest elements in a subarray. Instead of considering all possible subarrays, we leverage the fact that the **maximum sum must be formed by two consecutive elements**. This allows us to solve the problem efficiently in **O(N) time**.

---

## ⚙️ Approach
1. **Iterate Through the Array**: Traverse the array while maintaining a variable to track the maximum sum encountered so far.
2. **Compare Adjacent Elements**: At each index `i`, compute `arr[i] + arr[i+1]` and update the maximum sum if it's greater than the current maximum.
3. **Return the Maximum Found**: After iterating through the array, return the highest sum found.

---

## ✅ Code Implementation
```java
class Solution {
    // Function to find pair with maximum sum
    public int pairWithMaxSum(int arr[]) {
        
        int max = Integer.MIN_VALUE;
        for(int i=0; i<arr.length-1; i++){
            max = Math.max(max, arr[i]+arr[i+1]);
        }
        return max;
    }
}
```

---

## ⏳ Complexity Analysis

### **Time Complexity: O(N)**
- We traverse the array once, making this an **O(N)** solution.

### **Space Complexity: O(1)**
- We use only a single integer variable to store the maximum sum, resulting in **constant space usage**.

---

## 📊 Example Walkthrough

### **Example 1:**
**Input:**
```text
arr = [1, 9, 3, 5, 7]
```
**Processing:**
- (1 + 9) = 10
- (9 + 3) = 12
- (3 + 5) = 8
- (5 + 7) = 12
- Maximum sum = **12**

**Output:**
```text
12
```

---

## 🚀 Final Thoughts
- **Efficient O(N) approach** ensures fast computation.
- **Uses only a single pass and constant space**.
- **Simple yet effective technique for adjacent pair sum problems**.