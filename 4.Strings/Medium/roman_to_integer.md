# 🔍 Roman to Integer - Efficient Conversion Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/roman-to-integer/description/)

Given a Roman numeral, convert it to an integer. Roman numerals are represented by seven different symbols with the following values:

```
I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
```

**Constraints:**
- 1 <= s.length <= 15
- s contains only ('I', 'V', 'X', 'L', 'C', 'D', 'M')
- It is guaranteed that s is a valid Roman numeral

**Example 1:**
```text
Input: "III"
Output: 3
```

**Example 2:**
```text
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90, IV = 4
```

---

## 🧠 Intuition
The solution uses these key insights:
1. **Right-to-Left Processing**: Simplifies subtraction cases (IV, IX, etc.)
2. **Subtractive Principle**: When a smaller numeral appears before a larger one
3. **Efficient Comparison**: 4× current value check determines add/subtract

Key Observations:
- Processing backwards avoids lookahead
- The 4× rule elegantly handles all subtraction cases
- Single pass through string is sufficient

---

## ⚙️ Approach
### **1️⃣ Initialization**
1. Start from the end of the string
2. Initialize result accumulator

### **2️⃣ Character Processing**
1. For each character (right to left):
   - Convert to its numeric value
   - Apply subtractive principle using 4× rule
2. Update result based on current value

### **3️⃣ Result**
- Return accumulated integer value

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public int romanToInt(String s) {
        int total = 0;
        int prevValue = 0;
        
        // Process from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = getValue(s.charAt(i));
            
            // Apply subtractive principle
            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }
            
            prevValue = currentValue;
        }
        
        return total;
    }
    
    private int getValue(char c) {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
```

Key Improvements:
1. **Separate Value Mapping**: Clean `getValue` method
2. **Clearer Logic**: Explicit comparison instead of 4× rule
3. **Better Naming**: `total` and `prevValue` variables
4. **Default Case**: Handles invalid characters gracefully

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through string |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** "MCMXCIV"

**Execution Steps:**
1. Start from right (V=5):
   - total=5, prev=5
2. I=1 < prev → subtract:
   - total=4, prev=1
3. C=100 > prev → add:
   - total=104, prev=100
4. X=10 < prev → subtract:
   - total=94, prev=10
5. M=1000 > prev → add:
   - total=1094, prev=1000
6. C=100 < prev → subtract:
   - total=994, prev=100
7. M=1000 > prev → add:
   - total=1994

**Result:** 1994

---

## 💡 Key Features
- **Efficient Processing**: Single right-to-left pass
- **Clear Subtraction Logic**: Direct comparison
- **Modular Design**: Separate value mapping
- **Optimal Performance**: Linear time complexity

---

## 🚀 When to Use
- Roman numeral conversion problems
- When processing order affects interpretation
- Problems with simple symbol-to-value mapping

## ⚠️ Edge Cases
- **Single character**: Returns direct value
- **Repeated characters**: Handles additive cases
- **Subtractive combinations**: Correctly processes IV, IX, etc.
- **Maximum value**: Handles MMMCMXCIX (3999)

## 🛠 Variations
1. **Integer to Roman**:
```java
// Reverse conversion process
```

2. **Validation First**:
```java
// Check for invalid numerals first
```

3. **Extended Symbols**:
```java
// Support for larger Roman numerals
```