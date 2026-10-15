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
    Start["Start Task 6.3: Stream Array Input & Print"] --> ChooseMode{"Choose Paradigm: Iterative or Recursive?"}

    ChooseMode -- "Iterative" --> ItLoop["For i = 0 to n - 1"]
    ItLoop --> ItPrompt["Optional prompt: a[i] = "]
    ItPrompt --> ItRead["Read token into a[i] via scanner.next()"]
    ItRead --> ItCheck{"i == n - 1?"}
    ItCheck -- "No" --> ItLoop
    ItCheck -- "Yes" --> ItPrint["Format print array elements with fixed width"]

    ChooseMode -- "Recursive" --> RecCall["Recursive step at index i (initial: 0)"]
    RecCall --> BaseCase{"i >= n?"}
    BaseCase -- "Yes" --> Terminate["Return (stop recursion)"]
    BaseCase -- "No" --> RecPrompt["Optional prompt: a[i] = "]
    RecPrompt --> RecRead["Read token into a[i] via scanner.next()"]
    RecRead --> RecNext["Recursive call for index i + 1"]
    RecNext --> BaseCase

    ItPrint --> Finish["End"]
    Terminate --> RecPrint["Format print array elements recursively"]
    RecPrint --> Finish
```

### Activity / State Diagram (`stateDiagram-v2`)

```mermaid
stateDiagram-v2
    [*] --> Setup
    Setup --> ScannerInjection: "Inject Scanner (System.in or String Scanner)"
    ScannerInjection --> InputPhase

    state InputPhase {
        [*] --> CheckBoundary: "Check index i < n"
        CheckBoundary --> ReadToken: "scanner.next() extraction into a[i]"
        ReadToken --> AdvanceIndex: "Increment index (loop or tail call)"
        AdvanceIndex --> CheckBoundary
        CheckBoundary --> [*]: "i >= n"
    }

    InputPhase --> FormatPrintPhase: "Pass populated array to output formatter"

    state FormatPrintPhase {
        [*] --> FormatElement: "String.format(width, a[i])"
        FormatElement --> NextPrint: "Advance to i + 1"
        NextPrint --> FormatElement: "i < n"
        NextPrint --> [*]: "i >= n -> return formatted string"
    }

    FormatPrintPhase --> [*]
```

### Class Diagram (`classDiagram`)

```mermaid
classDiagram
    class IterativeInputService {
        +inputArray(array: Integer[], scanner: Scanner, promptWriter: Consumer~String~) void
        +formatArray(array: Integer[], fieldWidth: int) String
        +inputArrayTemplate~T~(array: T[], scanner: Scanner, parser: Function~String, T~, promptWriter: Consumer~String~) void
        +formatArrayTemplate~T~(array: T[], fieldWidth: int) String
    }

    class RecursiveInputService {
        +inputArray(array: Integer[], index: int, scanner: Scanner, promptWriter: Consumer~String~) void
        +formatArray(array: Integer[], index: int, fieldWidth: int) String
        +inputArrayTemplate~T~(array: T[], index: int, scanner: Scanner, parser: Function~String, T~, promptWriter: Consumer~String~) void
        +formatArrayTemplate~T~(array: T[], index: int, fieldWidth: int) String
    }

    class Task3Runner {
        +run() void
    }

    Task3Runner ..> IterativeInputService : uses
    Task3Runner ..> RecursiveInputService : uses
```
