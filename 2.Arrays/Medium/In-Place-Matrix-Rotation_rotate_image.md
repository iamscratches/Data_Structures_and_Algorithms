# 🚀 Rotate Image - In-Place Matrix Rotation

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/rotate-image/)

Given an n×n 2D matrix representing an image, rotate the image by 90 degrees clockwise in-place.

**Constraints:**
- n == matrix.length == matrix[i].length
- 1 ≤ n ≤ 20
- -1000 ≤ matrix[i][j] ≤ 1000

**Example 1:**
```text
Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
```

**Example 2:**
```text
Input: [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
```

---

## 🧠 Intuition
The optimal in-place rotation:
1. **Layered Approach**: Treat matrix as concentric layers
2. **Four-Way Swapping**: Rotates elements in groups of four
3. **Boundary Shrinking**: Reduces layer size with each iteration

Key Insights:
- Rotation can be achieved by swapping four elements at a time
- Only need to process n/2 layers
- Each element moves to (row, col) → (col, n-1-row)

---

## ⚙️ Approach
### **1️⃣ Layer-by-Layer Rotation**
1. For each layer from outer to inner:
   - Calculate boundaries (top, right, bottom, left)
   - For each position in layer:
     - Perform 4-way swap:
       - Right → Top
       - Bottom → Right
       - Left → Bottom
       - Top → Left

### **2️⃣ Four-Way Swap Logic**
- Uses temporary variables to store elements during rotation
- Handles all four edges simultaneously
- Preserves values while rotating

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i]; // save top
                
                // left -> top
                matrix[first][i] = matrix[last-offset][first];
                
                // bottom -> left
                matrix[last-offset][first] = matrix[last][last-offset];
                
                // right -> bottom
                matrix[last][last-offset] = matrix[i][last];
                
                // top -> right
                matrix[i][last] = top;
            }
        }
    }
}
```

Key Components:
1. **Layer Iteration**: Processes from outer to inner
2. **Boundary Calculation**: Determines current layer size
3. **Four-Way Swap**: Rotates elements in one operation
4. **Offset Handling**: Manages position within layer

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n²)      | Each element processed once |
| **Space**       | O(1)       | In-place rotation |

---

## 📊 Example Walkthrough

**Input:** `[[1,2,3],[4,5,6],[7,8,9]]`

**Execution Steps:**
1. Layer 0 (outer):
   - Swap positions: (0,0)→(0,2)→(2,2)→(2,0)
   - Swap values: 1→3→9→7→1
   - Next in layer: (0,1)→(1,2)→(2,1)→(1,0)
   - Swap values: 2→6→8→4→2
2. Result: `[[7,4,1],[8,5,2],[9,6,3]]`

---

## 💡 Key Features
- **In-Place Operation**: No additional matrix needed
- **Optimal Efficiency**: O(n²) time complexity
- **Clear Layer Logic**: Easy to understand boundaries
- **Generalizable**: Works for any n×n matrix

---

## 🚀 When to Use
- **Matrix rotation problems**
- **In-place algorithm requirements**
- **Image processing applications**
- **As building block** for other matrix operations

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Layer Rotation (this) | O(n²)    | O(1)             | Optimal space |
| Additional Matrix | O(n²)    | O(n²)           | Simpler but wasteful |
| Transpose+Reverse | O(n²)    | O(1)             | Alternative method |

## ⚠️ Edge Cases
- **1×1 matrix**: Returns unchanged
- **2×2 matrix**: Simple four-element swap
- **Even/Odd sizes**: Handled by n/2 layers
- **Large matrices**: Works within constraints

## 🛠 Variations
1. **Counter-Clockwise Rotation**:
```java
// Reverse the swap directions
```

2. **180° Rotation**:
```java
// Combine two 90° rotations
```

3. **Arbitrary Angle Rotation**:
```java
// More complex transformation required
```

This solution demonstrates an elegant pattern for matrix rotation problems, combining efficient in-place operations with clear boundary management.