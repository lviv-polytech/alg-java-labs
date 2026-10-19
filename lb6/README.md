# Lab 6: One-Dimensional Arrays (Java)

This module provides modular implementations of 1D array operations, contrasting iterative loops with pure recursive algorithms.

## 1. Subtasks Overview

1. **Task 6.1 (Variant 17): Array Selection, Counting, Summing, and Zeroing**
   - **Data Domain:** 25 integer elements uniformly distributed in $[4, 73]$.
   - **Selection Criterion:** $(r_i \bmod 6 = 0) \lor (i \bmod 5 \neq 0)$.
   - **Invariants:**
     - Matching count: $20 \le K \le 25$.
     - Cumulative sum: $80 \le S \le 1825$.
     - Call sequence: $\text{Generate} \to \text{Print} \to (\text{Count}, \text{Sum}) \to \text{Zero} \to \text{Print}$.
   - **Decomposition:** Single-responsibility methods across `IterativeArrayService` and `RecursiveArrayService` (`generate`, `formatArray`, `countElements`, `sumElements`, `zeroElements`).

2. **Task 6.2 (Variant 17): Search for the Index of the Maximum Even Element**
   - **Data Domain:** One-dimensional array $a = (a_0, \dots, a_{n-1})$ with $a_i \in [-50, 50]$.
   - **Mathematical Model:**
     - Parity predicate: $P(x) \iff (x \bmod 2 = 0)$ (0 and negative evens are included).
     - Missing even elements ($I_{\text{even}} = \varnothing$): returns sentinel value $-1$.
     - Duplicate maximum even values: returns the index of the first occurrence: $k^* = \min \{ i \in I_{\text{even}} \mid a_i = M \}$.
   - **Decomposition:** `generate`, `formatArray`, `findMaxEvenIndex` in `IterativeMaxEvenService` and `RecursiveMaxEvenService`.

3. **Task 6.3: Stream & Keyboard Array Input with Ordinary and Generic/Template Functions**
   - **Data Domain:** Sequential population and formatted output of 1D arrays of size $n$ via `Scanner` / input streams.
   - **Dual Typification:**
     - **Part A (Ordinary Functions):** Type-specialized for `Integer`: `inputArray`, `formatArray` (iterative and recursive).
     - **Part B (Generic Functions):** Parametric polymorphism for generic type `T` (`Integer`, `Double`, `String`): `inputArrayTemplate`, `formatArrayTemplate` (iterative and recursive).
   - **Deterministic Testing:** Fully decoupled from interactive standard input via injected `Scanner` instances over `String` streams.

4. **Task 6.4: Real Dynamic Array Analysis and In-Place Stable Partition**
   - **Data Domain:** Dynamically allocated real array $a = (a_0, \dots, a_{n-1})$ of size $n$ (`double[]`).
   - **Mathematical Calculations:**
     - Positive elements count: $C_{+} = \sum_{i=0}^{n-1} \chi_{+}(a_i)$, where $\chi_{+}(x) = 1$ if $x > 0$ else $0$.
     - Last zero search: $k_{\text{last}} = \max \{ i \mid |a_i| < 10^{-9} \}$ (or $-1$ if absent).
     - Sum after last zero: $S_{\text{after}} = \sum_{j = k_{\text{last}} + 1}^{n - 1} a_j$ (returns $0.0$ if $k_{\text{last}} = -1$ or $k_{\text{last}} = n - 1$).
   - **In-Place Stable Partition:**
     - Partition predicate: $\text{trunc}(x) \le 1 \iff x < 2.0$.
     - Reorders elements so that elements with $\text{trunc}(x) \le 1$ precede all other elements, maintaining their relative order ($O(1)$ auxiliary space).
   - **Decomposition:** `generate`, `formatArray`, `countPositiveElements`, `findLastZeroIndex`, `sumAfterLastZero`, `stableRestructure` across `IterativeArrayModifier` and `RecursiveArrayModifier`.

## 2. Compilation and Execution

```bash
# Build and run tests
./mvnw clean test -pl lb6

# Run interactive console application
./mvnw compile exec:java -pl lb6 -Dexec.mainClass="com.lpnu.lb6.ui.Main"
```

## 3. Diagrams (Mermaid.js)

### Flowchart (`flowchart TD`)

```mermaid
flowchart TD
    Start["Start Task 6.4: Dynamic Array Operations"] --> InputSize["Enter array size n, allocate double[n]"]
    InputSize --> InitArr["Generate random real values in [-5.0, 5.0]"]
    InitArr --> PrintInit["Print initial formatted array (width = 8, prec = 2)"]
    PrintInit --> CountPos["Calculate count of positive elements (x > 0.0)"]
    CountPos --> FindZero["Find last zero index k_last (from right to left)"]
    FindZero --> CheckZero{"k_last >= 0 && k_last < n - 1?"}
    CheckZero -- "Yes" --> SumElements["Sum elements from k_last + 1 to n - 1"]
    CheckZero -- "No" --> ZeroSum["Sum after last zero = 0.0"]
    SumElements --> Partition["In-place stable partition: trunc(x) <= 1 before others"]
    ZeroSum --> Partition
    Partition --> PrintFinal["Print modified formatted array with identical format"]
    PrintFinal --> Finish["End"]
```

### Activity / State Diagram (`stateDiagram-v2`)

```mermaid
stateDiagram-v2
    [*] --> Allocation
    Allocation --> DataInitialization: "Generate n real elements in [-5.0, 5.0]"
    DataInitialization --> DisplayInitial: "Formatted print to stdout"
    DisplayInitial --> AnalysisPhase

    state AnalysisPhase {
        [*] --> ComputePositive: "Count positive elements (x > 0)"
        ComputePositive --> LocateLastZero: "Search for last zero (right-to-left)"
        LocateLastZero --> ComputeSumAfter: "Sum suffix strictly after last zero"
        ComputeSumAfter --> [*]
    }

    AnalysisPhase --> InPlacePartition: "Stable partition: shift elements with trunc(x) <= 1"
    InPlacePartition --> DisplayModified: "Formatted print of partitioned array"
    DisplayModified --> [*]
```

### Class Diagram (`classDiagram`)

```mermaid
classDiagram
    class IterativeArrayModifier {
        +generate(array: double[], min: int, max: int) void
        +formatArray(array: double[]) String
        +countPositiveElements(array: double[]) int
        +findLastZeroIndex(array: double[]) int
        +sumAfterLastZero(array: double[]) double
        +stableRestructure(array: double[]) void
    }

    class RecursiveArrayModifier {
        +generate(array: double[], min: int, max: int, index: int) void
        +formatArray(array: double[], index: int) String
        +countPositiveElements(array: double[], index: int) int
        +findLastZeroIndex(array: double[], index: int) int
        +sumAfterLastZero(array: double[]) double
        +stableRestructure(array: double[]) void
    }

    class Task4Runner {
        +run() void
    }

    Task4Runner ..> IterativeArrayModifier : uses
    Task4Runner ..> RecursiveArrayModifier : uses
```
