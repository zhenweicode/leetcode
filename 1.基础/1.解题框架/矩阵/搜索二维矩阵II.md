https://leetcode.cn/problems/search-a-2d-matrix-ii/description/?envType=study-plan-v2&envId=top-100-liked

# 搜索二维矩阵 II（Java 详细解法）
## 一、问题描述
编写一个高效的算法来搜索 `m x n` 矩阵 `matrix` 中的一个目标值 `target`。该矩阵具有以下特性：
1. 每行的元素从左到右升序排列；
2. 每列的元素从上到下升序排列。

### 核心要求：
1. 时间复杂度尽可能优化（避免暴力遍历 O(mn)）；
2. 空间复杂度最优为 O(1)（原地搜索，无额外辅助空间）；
3. 适配边界情况：矩阵为空、单行/单列矩阵、目标值小于最小值或大于最大值。

### 示例：
#### 示例 1
- 输入矩阵：
  ```
  [
    [1,4,7,11,15],
    [2,5,8,12,19],
    [3,6,9,16,22],
    [10,13,14,17,24],
    [18,21,23,26,30]
  ]
  ```
- 目标值 `target = 5` → 输出：`true`（存在）；
- 目标值 `target = 20` → 输出：`false`（不存在）。

#### 示例 2
- 输入：`matrix = [[1,4],[2,5]], target = 2` → 输出：`true`；
- 输入：`matrix = [[1]], target = 1` → 输出：`true`；
- 输入：`matrix = [[1,3,5]], target = 4` → 输出：`false`。

## 二、问题分析
### 核心难点：
矩阵仅满足“行升序、列升序”，但**不满足“下一行首个元素大于上一行末尾元素”**（与“搜索二维矩阵 I”的区别），因此无法直接将矩阵 flatten 为一维数组进行二分查找。

### 关键观察（突破口）：
矩阵的 **右上角元素** 或 **左下角元素** 具有“双向单调性”，可作为搜索起点：
- 右上角元素 `matrix[i][j]`：
  - 若 `target > matrix[i][j]`：目标值不可能在当前行（行升序），需向下移动一行（`i++`）；
  - 若 `target < matrix[i][j]`：目标值不可能在当前列（列升序），需向左移动一列（`j--`）；
  - 若相等：找到目标，返回 `true`。
- 左下角元素 `matrix[i][j]`：
  - 若 `target > matrix[i][j]`：目标值不可能在当前列，需向右移动一列（`j++`）；
  - 若 `target < matrix[i][j]`：目标值不可能在当前行，需向上移动一行（`i--`）；
  - 若相等：找到目标，返回 `true`。

### 核心思路：
选择 **右上角元素作为搜索起点**（逻辑更直观），通过“行/列的单向排除”缩小搜索范围，每一步排除一行或一列，最终时间复杂度 O(m + n)（最坏情况遍历一行一列）。

## 三、详细解法（右上角起点法）
### 1. 算法步骤
#### 步骤 1：初始化边界
- 定义行指针 `i = 0`（从第一行开始）；
- 定义列指针 `j = matrix[0].length - 1`（从最后一列开始，即右上角）；
- 边界条件：`i < matrix.length`（未超出下边界）且 `j >= 0`（未超出左边界）。

#### 步骤 2：循环搜索
在边界范围内循环：
1. 若 `matrix[i][j] == target`：找到目标，返回 `true`；
2. 若 `matrix[i][j] < target`：当前行所有元素均小于目标（行升序），排除当前行，`i++`（向下移动）；
3. 若 `matrix[i][j] > target`：当前列所有元素均大于目标（列升序），排除当前列，`j--`（向左移动）。

#### 步骤 3：未找到目标
若循环结束仍未匹配，返回 `false`。

### 2. 示例演示（target = 5）
输入矩阵（右上角元素为 `matrix[0][4] = 15`）：
```
[
  [1,4,7,11,15],  // i=0
  [2,5,8,12,19],  // i=1
  [3,6,9,16,22],  // i=2
  [10,13,14,17,24],
  [18,21,23,26,30]
]
```
- 初始：`i=0, j=4` → `15 > 5` → 排除列 4，`j=3`（元素 11）；
- `i=0, j=3` → `11 > 5` → 排除列 3，`j=2`（元素 7）；
- `i=0, j=2` → `7 > 5` → 排除列 2，`j=1`（元素 4）；
- `i=0, j=1` → `4 < 5` → 排除行 0，`i=1`（元素 5）；
- `i=1, j=1` → `5 == 5` → 找到目标，返回 `true`。

### 3. Java 代码实现
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 边界处理：矩阵为空、行长度为 0、列长度为 0
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;    // 行数
        int n = matrix[0].length; // 列数

        int i = 0;                // 行指针（初始第一行）
        int j = n - 1;            // 列指针（初始最后一列，右上角）

        // 循环条件：未超出下边界（i < m）且未超出左边界（j >= 0）
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true; // 找到目标
            } else if (matrix[i][j] < target) {
                i++; // 目标更大，排除当前行，向下移动
            } else {
                j--; // 目标更小，排除当前列，向左移动
            }
        }

        // 循环结束未找到，返回 false
        return false;
    }
}
```

### 4. 关键细节解释
- **边界处理优先级最高**：先判断矩阵是否为空（`matrix == null`）、行数为 0（`matrix.length == 0`）或列数为 0（`matrix[0].length == 0`），避免数组越界；
- **指针移动逻辑**：每一步仅移动一个指针（`i++` 或 `j--`），确保每一步排除一行或一列，搜索范围严格缩小；
- **无重复遍历**：指针移动方向唯一（向下或向左），不会重复访问同一元素。

## 四、备选解法（左下角起点法）
### 1. 核心思路
选择左下角元素作为搜索起点，逻辑与右上角法对称：
- 若 `target > matrix[i][j]`：目标值不可能在当前列（列升序），向右移动一列（`j++`）；
- 若 `target < matrix[i][j]`：目标值不可能在当前行（行升序），向上移动一行（`i--`）；
- 若相等：返回 `true`。

### 2. Java 代码实现
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int i = m - 1; // 行指针（初始最后一行，左下角）
        int j = 0;     // 列指针（初始第一列）

        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++; // 目标更大，排除当前列，向右移动
            } else {
                i--; // 目标更小，排除当前行，向上移动
            }
        }

        return false;
    }
}
```

## 五、其他解法对比（了解即可）
### 1. 暴力遍历法（不推荐）
- 思路：双重循环遍历所有元素，逐一对比目标值；
- 代码：
  ```java
  public boolean searchMatrix(int[][] matrix, int target) {
      for (int[] row : matrix) {
          for (int num : row) {
              if (num == target) return true;
          }
      }
      return false;
  }
  ```
- 复杂度：时间 O(mn)，空间 O(1)；
- 缺点：未利用矩阵的升序特性，效率极低，不适用于大数据量矩阵。

### 2. 每行二分查找法（时间 O(m log n)）
- 思路：利用每行升序的特性，对每一行单独进行二分查找；
- 代码：
  ```java
  class Solution {
      public boolean searchMatrix(int[][] matrix, int target) {
          if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
              return false;
          }

          for (int[] row : matrix) {
              // 对当前行进行二分查找
              int left = 0, right = row.length - 1;
              while (left <= right) {
                  int mid = left + (right - left) / 2;
                  if (row[mid] == target) {
                      return true;
                  } else if (row[mid] < target) {
                      left = mid + 1;
                  } else {
                      right = mid - 1;
                  }
              }
          }
          return false;
      }
  }
  ```
- 复杂度：时间 O(m log n)（m 行，每行二分 O(log n)），空间 O(1)；
- 优点：逻辑简单，易于实现；
- 缺点：效率低于右上角法（O(m + n)），尤其是当 m 较大时。

## 六、复杂度分析
| 解法 | 时间复杂度 | 空间复杂度 | 核心优势 | 适用场景 |
|------|------------|------------|----------|----------|
| 右上角起点法 | O(m + n) | O(1) | 效率最优，逻辑直观 | 面试首选、工程实践 |
| 左下角起点法 | O(m + n) | O(1) | 与右上角法对称，思路灵活 | 拓展思路，避免思维固化 |
| 每行二分查找 | O(m log n) | O(1) | 利用二分特性，易于理解 | 行数量较少的矩阵 |
| 暴力遍历 | O(mn) | O(1) | 代码最简单 | 极小矩阵（仅作了解） |

## 七、常见错误点与注意事项
1. **忽略边界处理**：未判断矩阵为空或列数为 0，导致 `matrix[0].length` 空指针异常；
2. **指针边界错误**：循环条件写成 `i <= m` 或 `j >= n`，导致数组越界；
3. **混淆矩阵特性**：误将该矩阵当作“搜索二维矩阵 I”处理（直接二分整个矩阵），导致搜索失败；
4. **指针移动方向错误**：比如 `matrix[i][j] < target` 时向左移动 `j--`，导致搜索范围错误。
