# 🔍 String to Integer (atoi) - Robust Parsing Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/string-to-integer-atoi/description/)

Implement the `atoi` function which converts a string to a 32-bit signed integer. The algorithm should:
1. Read and ignore leading whitespace
2. Check for '+' or '-' sign
3. Read digits until non-digit or end of string
4. Convert digits to integer
5. Clamp result to 32-bit signed integer range

**Constraints:**
- 0 <= s.length <= 200
- s consists of English letters, digits, ' ', '+', '-', and '.'

**Example 1:**
```text
Input: "42"
Output: 42
```

**Example 2:**
```text
Input: "   -42"
Output: -42
```

**Example 3:**
```text
Input: "4193 with words"
Output: 4193
```

---

## 🧠 Intuition
The solution handles these key aspects:
1. **Whitespace Handling**: Leading spaces must be ignored
2. **Sign Detection**: Optional '+' or '-' at start
3. **Digit Parsing**: Convert consecutive digits to number
4. **Overflow Protection**: Clamp to 32-bit integer range
5. **Early Termination**: Stop at first non-digit character

Key Observations:
- The problem requires careful edge case handling
- Long type helps detect overflow before clamping
- Character-by-character processing is efficient

---

## ⚙️ Approach
### **1️⃣ Initial Setup**
1. Trim leading whitespace
2. Initialize result accumulator and sign flag

### **2️⃣ Sign Handling**
1. Check for optional '+' or '-' prefix
2. Set sign multiplier (-1 for negative)

### **3️⃣ Digit Processing**
1. Process each character until non-digit
2. Convert digit characters to numeric value
3. Accumulate value while checking for overflow

### **4️⃣ Result Clamping**
1. Apply sign to accumulated value
2. Clamp to 32-bit signed integer range
3. Return final result

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;
        
        int sign = 1;
        int index = 0;
        long result = 0;
        
        // Handle sign
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }
        
        // Process digits
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';
            result = result * 10 + digit;
            
            // Check for overflow
            if (result * sign > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (result * sign < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            
            index++;
        }
        
        return (int)(result * sign);
    }
}
```

Key Improvements:
1. **Early Empty Check**: Handles empty string after trim
2. **Character.isDigit()**: More readable than ASCII comparison
3. **Immediate Overflow Check**: Detects during accumulation
4. **Cleaner Sign Handling**: Ternary operator for clarity
5. **Proper Index Management**: Separate index variable

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through string |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** "   -4193 with words"

**Execution Steps:**
1. Trim: "-4193 with words"
2. Detect '-' sign: sign = -1
3. Process digits:
   - '4' → result = 4
   - '1' → result = 41
   - '9' → result = 419
   - '3' → result = 4193
4. Stop at space (non-digit)
5. Apply sign: -4193
6. Within 32-bit range → return -4193

**Result:** -4193

---

## 💡 Key Features
- **Robust Parsing**: Handles all edge cases
- **Efficient Processing**: Single pass through string
- **Overflow Protection**: Immediate clamping
- **Clean Code**: Readable and maintainable

---

## 🚀 When to Use
- String to number conversion
- When input validation is important
- Problems requiring careful edge case handling

## ⚠️ Edge Cases
- **Leading/trailing spaces**: Properly handled
- **Empty string**: Returns 0
- **Overflow values**: Clamped correctly
- **Mixed characters**: Stops at first non-digit
- **Sign prefixes**: Handles both '+' and '-'

## 🛠 Variations
1. **Strict Validation**:
```java
// Reject strings with any non-digit characters
```

2. **Decimal Support**:
```java
// Add handling for fractional numbers
```

3. **Custom Overflow**:
```java
// Return custom value on overflow
```