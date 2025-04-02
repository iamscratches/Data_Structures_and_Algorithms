# 🔍 Find Two Non-Repeating Numbers - Bit Manipulation Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/finding-the-numbers0215/1)

Given an array of integers where every number appears exactly twice except for two numbers that appear only once, find these two unique numbers.

**Constraints:**
- 2 ≤ N ≤ 10⁵
- 1 ≤ A[i] ≤ 10⁹

**Example 1:**
```text
Input: [1, 2, 3, 2, 1, 4]
Output: [3, 4]
Explanation: 3 and 4 appear once, others appear twice
```

**Example 2:**
```text
Input: [2, 1, 3, 2]
Output: [1, 3]
Explanation: 1 and 3 appear once, others appear twice
```

---

## 🧠 Intuition
The solution leverages bit manipulation:
1. **XOR Property**: XOR of all numbers gives XOR of the two unique numbers
2. **Rightmost Set Bit**: Identifies a differentiating bit between the two numbers
3. **Partitioning**: Groups numbers based on the set bit to separate the unique numbers

Key Insights:
- XOR cancels out duplicate numbers
- The set bit in the XOR result indicates differing bits between the two unique numbers
- Partitioning by this bit separates the numbers into two groups

---

## ⚙️ Approach
### **1️⃣ XOR All Numbers**
1. Compute XOR of all elements → gives `xor = num1 ^ num2`

### **2️⃣ Find Rightmost Set Bit**
1. Identify the first differing bit between the two numbers
2. This bit will be set in `xor`

### **3️⃣ Partition and XOR**
1. Divide numbers into two groups based on the set bit
2. XOR numbers in each group separately to find the unique numbers

### **4️⃣ Return Sorted Result**
1. Sort the two numbers before returning

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public List<Integer> singleNumber(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int xor = 0;
        
        // Step 1: XOR all numbers to get xor = num1 ^ num2
        for (int num : nums) {
            xor ^= num;
        }
        
        // Step 2: Find the rightmost set bit
        int bit = 0;
        while (((xor >> bit) & 1) != 1) {
            bit++;
        }
        
        // Step 3: Partition and XOR
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if (((num >> bit) & 1) == 1) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        
        // Step 4: Sort and return
        result.add(Math.min(num1, num2));
        result.add(Math.max(num1, num2));
        return result;
    }
}
```

Key Components:
1. **XOR Accumulation**: Cancels out duplicate numbers
2. **Bit Isolation**: Finds differentiating bit
3. **Partitioned XOR**: Separates and identifies unique numbers
4. **Result Sorting**: Returns numbers in ascending order

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Two linear passes |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `[1, 2, 3, 2, 1, 4]`

**Execution Steps:**
1. **XOR All Numbers**:
   - XOR = 1^2^3^2^1^4 = 3^4 = 7 (0111)
2. **Find Set Bit**:
   - Rightmost set bit is bit 0 (value 1)
3. **Partition and XOR**:
   - Group 1 (bit 0 set): 1 (01), 1 (01), 3 (11) → XOR = 3
   - Group 2: 2 (10), 2 (10), 4 (100) → XOR = 4
4. **Result**: `[3, 4]`

---

## 💡 Key Features
- **Efficient Bit Manipulation**: Uses XOR properties
- **No Extra Space**: Operates in constant space
- **Linear Time**: Processes array in two passes
- **Generalizable**: Can extend to similar problems

---

## 🚀 When to Use
- **Finding unique elements in arrays**
- **Problems requiring constant space**
- **When bit manipulation is applicable**
- **As building block** for more complex bit problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Bit Manipulation (this) | O(n) | O(1) | Most space efficient |
| Hash Map | O(n) | O(n) | Simpler but more space |
| Sorting | O(nlogn) | O(1) | Modifies input |

## ⚠️ Edge Cases
- **Minimum input size**: Handles N=2 case
- **Large numbers**: Works within constraint limits
- **All numbers same except two**: Correctly identifies
- **Numbers differ by one bit**: Still separates correctly

## 🛠 Variations
1. **Single Non-Repeating Number**:
```java
// Simple XOR of all numbers
```

2. **Three Unique Numbers**:
```java
// Requires more complex bit manipulation
```

3. **Find Missing Number**:
```java
// XOR with expected sequence
```

This solution demonstrates an elegant bit manipulation approach to efficiently find two unique numbers in linear time with constant space complexity. The method cleverly uses XOR properties and bit partitioning to isolate the desired numbers.