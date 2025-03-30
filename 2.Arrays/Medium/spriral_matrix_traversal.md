# 🌀 Spiral Matrix - Layer-by-Layer Traversal

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/spiral-matrix/)

Given an m×n matrix, return all elements of the matrix in spiral order.

**Constraints:**
- m == matrix.length
- n == matrix[i].length
- 1 ≤ m, n ≤ 10
- -100 ≤ matrix[i][j] ≤ 100

**Example 1:**
```text
Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
```

**Example 2:**
```text
Input: [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
```

---

## 🧠 Intuition
The optimal spiral order traversal:
1. **Boundary Shrinking**: Define and update four boundaries (Top, Right, Bottom, Left)
2. **Layer-by-Layer**: Process the matrix from outer layer to inner layers
3. **Directional Traversal**: Move right → down → left → up in sequence

Key Insights:
- Each full spiral cycle consists of four directional passes
- Boundaries collapse inward after each cycle
- Need to check boundary conditions after each direction

---

## ⚙️ Approach
### **1️⃣ Boundary Tracking Approach**
1. Initialize four boundaries (L, R, T, B)
2. While boundaries are valid:
   - Traverse right along top row (L to R)
   - Move top boundary down (T++)
   - Traverse down along right column (T to B)
   - Move right boundary left (R--)
   - Check if boundaries are still valid
   - Traverse left along bottom row (R to L)
   - Move bottom boundary up (B--)
   - Traverse up along left column (B to T)
   - Move left boundary right (L++)

### **2️⃣ Termination Condition**
- Loop continues while L ≤ R AND T ≤ B
- Early termination check after right/down passes

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public List<Integer> spiralOrder(int[][] mat) {
        ArrayList<Integer> list = new ArrayList<>();
        int L = 0, T = 0, R = mat[0].length-1, B = mat.length-1;
        
        while(L <= R && T <= B) {
            // Right pass
            for(int i = L; i <= R; i++) {
                list.add(mat[T][i]);
            }
            T++;
            
            // Down pass
            for(int i = T; i <= B; i++) {
                list.add(mat[i][R]);
            }
            R--;
            
            // Check if boundaries are still valid
            if(!(L <= R && T <= B)) break;
            
            // Left pass
            for(int i = R; i >= L; i--) {
                list.add(mat[B][i]);
            }
            B--;
            
            // Up pass
            for(int i = B; i >= T; i--) {
                list.add(mat[i][L]);
            }
            L++;
        }
        return list;
    }
}
```

Key Components:
1. **Boundary Initialization**: L, R, T, B define current layer
2. **Directional Passes**: Four distinct traversal directions
3. **Boundary Updates**: Shrink boundaries after each pass
4. **Early Termination Check**: Prevents duplicate traversal

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(m×n)     | Each element visited exactly once |
| **Space**       | O(1)       | Constant space (output excluded) |

---

## 📊 Example Walkthrough

**Input:** `[[1,2,3],[4,5,6],[7,8,9]]`

**Execution Steps:**
1. Initial boundaries: L=0, R=2, T=0, B=2
2. Right pass: 1→2→3 (T becomes 1)
3. Down pass: 6→9 (R becomes 1)
4. Boundaries valid (0≤1 and 1≤2)
5. Left pass: 8→7 (B becomes 1)
6. Up pass: 4 (L becomes 1)
7. Next iteration boundaries: L=1, R=1, T=1, B=1
8. Right pass: 5 (loop ends)

**Result:** `[1,2,3,6,9,8,7,4,5]`

---

## 💡 Key Features
- **Clear Boundary Logic**: Intuitive layer definition
- **Directional Isolation**: Each pass handles one direction
- **Early Termination**: Prevents duplicate processing
- **Generalizable**: Works for any m×n matrix

---

## 🚀 When to Use
- **Matrix traversal problems**
- **Spiral pattern generation**
- **Image processing applications**
- **As building block** for other matrix operations

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Boundary Tracking (this) | O(m×n) | O(1) | Most efficient |
| Recursive Layer Peel | O(m×n) | O(m×n) | Simpler but uses stack |
| Visited Marking | O(m×n) | O(m×n) | Straightforward but wasteful |

## ⚠️ Edge Cases
- **Single row matrix**: Only needs right pass
- **Single column matrix**: Only needs right+down passes
- **Rectangular matrices**: Handles m ≠ n cases
- **Large matrices**: Works within constraints

## 🛠 Variations
1. **Counter-Clockwise Spiral**:
```java
// Reverse the direction sequence
```

2. **2D Array Generation**:
```java
// Build matrix in spiral order
```

3. **Diagonal Spiral**:
```java
// More complex traversal pattern
```

This solution demonstrates an efficient pattern for spiral matrix traversal, combining clear boundary management with directional processing. The in-place approach makes it memory efficient while maintaining optimal time complexity.