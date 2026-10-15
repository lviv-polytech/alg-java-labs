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
2. **Task 6.2:** Extreme search for the index of the maximum even integer (returns `-1` if absent).
3. **Task 6.3:** Generic input scanners reading strongly typed arrays.
4. **Task 6.4:** Dynamic array manipulation: positive count, sum after last zero, and stable partition around value $2.0$.

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
    Start["Start Task 6.1 (Variant 17)"] --> Gen["Generate array: 25 integers in range [4, 73]"]
    Gen --> PrintInit["Format and display initial array (width = 4)"]
    PrintInit --> Process["Process elements: index i = 0 .. 24"]
    Process --> Check{"(r[i] % 6 == 0) || (i % 5 != 0)?"}
    Check -- "Yes" --> MatchAction["Increment count K, accumulate sum S"]
    Check -- "No" --> Keep["Keep element value"]
    MatchAction --> Step["Next index i = i + 1"]
    Keep --> Step
    Step --> HasMore{"i < 25?"}
    HasMore -- "Yes" --> Check
    HasMore -- "No" --> ZeroPhase["Zero out matched elements in-place"]
    ZeroPhase --> DisplayStats["Print Count K and Sum S"]
    DisplayStats --> PrintMod["Format and display modified array"]
    PrintMod --> Finish["End"]
```

### Activity / State Diagram (`stateDiagram-v2`)

```mermaid
stateDiagram-v2
    [*] --> Initialization
    Initialization --> GenerateArray: "Fill array in [4, 73]"
    GenerateArray --> PrintInitial: "Display formatted original state"
    PrintInitial --> Analysis

    state Analysis {
        [*] --> CountElements: "Count matching elements (iterative / recursive)"
        CountElements --> SumElements: "Sum matching elements (iterative / recursive)"
        SumElements --> ZeroElements: "Zero matched cells in-place"
        ZeroElements --> [*]
    }

    Analysis --> DisplayResults: "Output Count and Sum"
    DisplayResults --> PrintModified: "Display modified array with zeros"
    PrintModified --> [*]
```

### Class Diagram (`classDiagram`)

```mermaid
classDiagram
    class ArrayProcessResult {
        <<Record>>
        +count() int
        +sum() int
    }

    class IterativeArrayService {
        +generate(array: int[], min: int, max: int) void
        +formatArray(array: int[]) String
        +isSelected(index: int, value: int) boolean
        +countElements(array: int[]) int
        +sumElements(array: int[]) int
        +zeroElements(array: int[]) void
        +processAndModify(array: int[]) ArrayProcessResult
    }

    class RecursiveArrayService {
        +generate(array: int[], min: int, max: int, index: int) void
        +formatArray(array: int[], index: int) String
        +isSelected(index: int, value: int) boolean
        +countElements(array: int[], index: int) int
        +sumElements(array: int[], index: int) int
        +zeroElements(array: int[], index: int) void
        +processAndModify(array: int[], index: int) ArrayProcessResult
    }

    class Task1Runner {
        +run() void
    }

    Task1Runner --> IterativeArrayService
    Task1Runner --> RecursiveArrayService
    IterativeArrayService --> ArrayProcessResult : creates
    RecursiveArrayService --> ArrayProcessResult : creates
```
