# 🔍 Find Missing and Repeating Numbers - XOR Bit Manipulation Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1)

Given an unsorted array of size `n` containing numbers from 1 to `n`, find:
1. The number that appears twice (repeating)
2. The number that is missing

**Constraints:**
- 2 ≤ n ≤ 10⁵
- 1 ≤ a[i] ≤ n

**Example 1:**
```text
Input: [1, 3, 3]
Output: [3, 2]
Explanation: 3 appears twice, 2 is missing
```

**Example 2:**
```text
Input: [2, 2]
Output: [2, 1]
Explanation: 2 appears twice, 1 is missing
```

---

## 🧠 Intuition
The solution uses XOR bit manipulation:
1. **XOR Properties**: XOR of a number with itself is 0
2. **XOR All Elements**: Cancels out duplicates, leaves XOR of missing and repeating
3. **Partitioning**: Uses rightmost set bit to separate numbers into two groups
4. **Group XOR**: Finds the two numbers by XORing groups separately

Key Insights:
- XOR of all array elements and all numbers 1 to n gives `missing ^ repeating`
- The rightmost set bit differentiates the two numbers
- Partitioning allows separate identification of each number

---

## ⚙️ Approach
### **1️⃣ Compute XOR of Array and Range**
1. XOR all numbers in the array
2. XOR result with all numbers from 1 to n → gives `x = missing ^ repeating`

### **2️⃣ Find Rightmost Set Bit**
1. Identify the first differing bit between missing and repeating
2. This bit will be set in `x`

### **3️⃣ Partition Numbers**
1. Divide numbers into two groups based on the set bit
2. XOR array elements and range numbers separately in each group

### **4️⃣ Identify Numbers**
1. The two results from partitioning are the missing and repeating numbers
2. Determine which is which by checking the original array

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public ArrayList<Integer> findTwoElement(int a[]) {
        int n = a.length;
        int x = 0; // Will hold missing ^ repeating
        int y = 0; // Will hold one of the numbers
        int bit = 0;
        
        // Step 1: XOR all array elements and numbers 1..n
        for (int i = 1; i <= n; i++) {
            x ^= i;
        }
        for (int num : a) {
            x ^= num;
        }
        
        // Step 2: Find rightmost set bit in x
        while (((x >> bit) & 1) != 1) {
            bit++;
        }
        
        // Step 3: Partition and XOR
        for (int num : a) {
            if (((num >> bit) & 1) == 0) {
                y ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (((i >> bit) & 1) == 0) {
                y ^= i;
            }
        }
        
        // Step 4: Determine which is missing and repeating
        x = x ^ y; // x now holds the other number
        ArrayList<Integer> res = new ArrayList<>();
        
        // Check which number is repeating
        for (int i = 0; i < n; i++) {
            if (a[i] == x) {
                res.add(x); // Repeating first
                res.add(y); // Then missing
                return res;
            } else if (a[i] == y) {
                res.add(y); // Repeating first
                res.add(x); // Then missing
                return res;
            }
        }
        
        return res;
    }
}
```

Key Components:
1. **XOR Computation**: Combines array and range XORs
2. **Bit Isolation**: Finds differentiating bit
3. **Partitioned XOR**: Separates and identifies candidates
4. **Verification**: Checks original array to determine order

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Four linear passes |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `[1, 3, 3]`

**Execution Steps:**
1. **XOR Computation**:
   - XOR 1..3: 1^2^3 = 0
   - XOR array: 0^1^3^3 = 1
   - Final x = 0^1 = 1 (missing ^ repeating)
2. **Find Set Bit**:
   - Rightmost set bit in 1 (0001) is bit 0
3. **Partition and XOR**:
   - Group 0 (bit 0 not set): array(1), range(2) → y = 1^2 = 3
   - x = 1^3 = 2
4. **Verification**:
   - 3 appears twice in array → repeating is 3, missing is 2
5. **Result**: `[3, 2]`

---

## 💡 Key Features
- **Bit Manipulation**: Efficient use of XOR properties
- **No Extra Space**: Operates in constant space
- **Linear Time**: Processes array in multiple passes
- **Generalizable**: Can extend to similar problems

---

## 🚀 When to Use
- **Finding duplicate/missing numbers**
- **Problems requiring constant space**
- **When bit manipulation is applicable**
- **As building block** for more complex problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| XOR Bit Manipulation (this) | O(n) | O(1) | Most space efficient |
| Mathematical Sum | O(n) | O(1) | Simpler but may overflow |
| Hash Map | O(n) | O(n) | Easier to implement |

## ⚠️ Edge Cases
- **Smallest array (n=2)**: Handles correctly
- **Large numbers**: Works within constraint limits
- **Missing first/last element**: Correctly identifies
- **All elements same except one**: Handles properly

## 🛠 Variations
1. **Single Missing Number**:
```java
// Simple XOR of array and range
```

2. **Two Missing Numbers**:
```java
// Similar approach with additional partitioning
```

3. **Find All Duplicates**:
```java
// Extend to find multiple duplicates
```

This solution demonstrates an elegant bit manipulation approach to efficiently find the missing and repeating numbers in linear time with constant space complexity. The method cleverly uses XOR properties and bit partitioning to isolate the desired numbers.