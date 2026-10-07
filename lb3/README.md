# LB3 — Variant 17

This laboratory work contains four separate tasks combined in a single CLI application launched from `Main`. Each task is implemented in a dedicated package to make the structure clearer and easier to maintain.

## Project structure

- `var/task1` — Task 1: single-variable function evaluation
- `var/task2` — Task 2: piecewise function `F(x)`
- `var/task3` — Task 3: graph-defined function `y = f(x)`
- `var/task4` — Task 4: point region checker (determines if a point lies within a shaded region)
- `Main.java` — entry point that reads inputs for all tasks and prints labeled results

## Architecture diagrams

### Task 1 — flowchart

```mermaid
flowchart TD
    A[Start] --> B[Read m, n]
    B --> C{m < 4}
    C -- Yes --> D["Calculate 4*m^7 - m^5 + m^3 - 2"]
    C -- No --> E{m < 7}
    E -- Yes --> F["Calculate arctan((abs(m) + 1)/2) + 8.3*m"]
    E -- No --> G["Calculate ln(abs(2*m + e^(4*m + 1)))"]
    D --> H["Add m^2 + 1"]
    F --> H
    G --> H
    H --> I[Print y]
    I --> J[End]
```

### Task 1 — UML activity diagram

```mermaid
stateDiagram-v2
    [*] --> ReadInput
    ReadInput --> Check_m_lt_4
    Check_m_lt_4 --> Branch1: m < 4
    Check_m_lt_4 --> Check_m_lt_7: m >= 4
    Branch1 --> Evaluate1
    Check_m_lt_7 --> Branch2: m < 7
    Branch2 --> Evaluate2
    Check_m_lt_7 --> Branch3: m >= 7
    Branch3 --> Evaluate3
    Evaluate1 --> Combine
    Evaluate2 --> Combine
    Evaluate3 --> Combine
    Combine --> Print
    Print --> [*]
```

### Task 2 — flowchart

```mermaid
flowchart TD
    A[Start] --> B[Read a, b, c, x]
    B --> C{"(x + 10 < 0) and (b != 0)"}
    C -- Yes --> D["Return a*x^2 - c*x + b"]
    C -- No --> E{"(x + 10 > 0) and (b == 0)"}
    E -- Yes --> F["Return (x - a)/(x - c)"]
    E -- No --> G["Return -x/(a - c)"]
    D --> H[Compare short and full branch results]
    F --> H
    G --> H
    H --> I{Match?}
    I -- Yes --> J[Print final result]
    I -- No --> K[Throw inconsistency exception]
    J --> L[End]
    K --> L
```

### Task 2 — UML activity diagram

```mermaid
stateDiagram-v2
    [*] --> ReadParams
    ReadParams --> EvaluateCondition1
    EvaluateCondition1 --> BranchA
    BranchA --> CompareResults
    CompareResults --> Outcome
    Outcome --> [*]
```

### Task 3 — flowchart

```mermaid
flowchart TD
    A[Start] --> B[Read x, R]
    B --> C{"x <= -1 - R"}
    C -- Yes --> D[Return 1]
    C -- No --> E{"x <= -1"}
    E -- Yes --> F["Return -sqrt(R^2 - (x + 1)^2)"]
    E -- No --> G{"x <= 2"}
    G -- Yes --> H["Return -R"]
    G -- No --> I["Return R*(x - 4)/2"]
    D --> J[Print y]
    F --> J
    H --> J
    I --> J
    J --> K[End]
```

### Task 3 — UML activity diagram

```mermaid
stateDiagram-v2
    [*] --> ReadInputs
    ReadInputs --> Check1
    Check1 --> Branch1
    Branch1 --> Output1
    Check1 --> Check2
    Check2 --> Branch2
    Branch2 --> Output2
    Check2 --> Check3
    Check3 --> Branch3
    Branch3 --> Output3
    Output1 --> [*]
    Output2 --> [*]
    Output3 --> [*]
```

## Task 1 — Single-variable function

Input values: `m`, `n`

The function is built as:

$$Y = m^2 + 1 + \Phi(m)$$

where the branch logic is:

$$\Phi(m) = \begin{cases} 4m^7 - m^5 + m^3 - 2, & m < 4 \\ \arctan\left(\frac{\vert{}m\vert{} + 1}{2}\right) + 8.3m, & 4 \le m < 7 \\ \ln\left(\vert{}2m + e^{4m + 1}\vert{}\right), & m \ge 7 \end{cases}$$

Implementation: `SingleVariableExpression` in package `com.lpnu.alg_lb3.var.task1`.

## Task 2 — Piecewise function `F(x)`

Input values: `a`, `b`, `c`, `x`

The function is implemented in both short and full conditional forms, and the two results are checked for equality:

$$F(x) = \begin{cases} ax^2 - cx + b, & x + 10 < 0 \ \text{and } b \ne 0 \\ \dfrac{x - a}{x - c}, & x + 10 > 0 \ \text{and } b = 0 \\ -\dfrac{x}{a - c}, & \text{otherwise} \end{cases}$$

Implementation: `PiecewiseExpression`, `ShortFormExpression`, `FullFormExpression`, `PiecewiseFunction`, and `SevenTeen` in package `com.lpnu.alg_lb3.var.task2`.

## Task 3 — Graph-defined function `y = f(x)`

Input values: `x`, `R`

The graph is divided into the following branches:

$$y = \begin{cases} 1, & x \le -1 - R \\ -\sqrt{R^2 - (x + 1)^2}, & -1 - R < x \le -1 \\ -R, & -1 < x \le 2 \\ \dfrac{R(x - 4)}{2}, & x > 2 \end{cases}$$

Implementation: `GraphFunctionCalculator` in package `com.lpnu.alg_lb3.var.task3`.

## Task 4 — Point Region Checker

Input values: `x`, `y`, `R`

Given a point with coordinates `(x, y)` and a radius parameter `R`, determine whether the point lies within the shaded region composed of two quarter-circle segments:

- **Region A (Quadrant II, top-left):** A quarter-circle centered at `(-R, 0)` with radius `R`. Constraints:
  - `-R ≤ x ≤ 0`
  - `0 ≤ y ≤ R`
  - `(x + R)² + y² ≤ R²`

- **Region B (Quadrant IV, bottom-right):** A quarter-circle centered at `(R, 0)` with radius `R`. Constraints:
  - `0 ≤ x ≤ R`
  - `-R ≤ y ≤ 0`
  - `(x - R)² + y² ≤ R²`

Implementation: `PointRegionChecker` interface and `PointRegionCheckerImpl` (package-private) in package `com.lpnu.alg_lb3.var.task4`.

The result is printed as a text message indicating whether the point is **INSIDE** or **OUTSIDE** the shaded region.

### Task 4 — flowchart

```mermaid
flowchart TD
    A[Start] --> B[Read x, y, R]
    B --> C{R > 0 and finite?}
    C -- No --> D[Throw IllegalArgumentException]
    C -- Yes --> E{x or y is NaN/Infinite?}
    E -- Yes --> F[Return false]
    E -- No --> G{Check Quadrant II}
    G --> H["(-R ≤ x ≤ 0) and (0 ≤ y ≤ R)"]
    H --> I{"(x + R)² + y² ≤ R²"}
    I -- Yes --> J[Return true]
    I -- No --> K{Check Quadrant IV}
    K --> L["(0 ≤ x ≤ R) and (-R ≤ y ≤ 0)"]
    L --> M{"(x - R)² + y² ≤ R²"}
    M -- Yes --> N[Return true]
    M -- No --> O[Return false]
    J --> P[Print result]
    N --> P
    F --> P
    O --> P
    P --> Q[End]
    D --> Q
```

### Task 4 — UML activity diagram

```mermaid
stateDiagram-v2
    [*] --> ReadInput
    ReadInput --> ValidateRadius
    ValidateRadius --> CheckFiniteCoordinates: valid
    ValidateRadius --> ThrowException: invalid
    ThrowException --> [*]
    CheckFiniteCoordinates --> CheckQuadrantII: finite
    CheckFiniteCoordinates --> ReturnFalse: not finite
    CheckQuadrantII --> InQuadrantII: inside
    CheckQuadrantII --> CheckQuadrantIV: not inside
    InQuadrantII --> ReturnTrue
    CheckQuadrantIV --> InQuadrantIV: inside
    CheckQuadrantIV --> ReturnFalse: not inside
    InQuadrantIV --> ReturnTrue
    ReturnTrue --> [*]
    ReturnFalse --> [*]
```

### Structural Diagram (classDiagram)

```mermaid
classDiagram
    class Main {
      +static void main(String[] args)
    }
    class SevenTeen {
      +Lb3ExpressionResult expression(InParams params)
      +evaluateSingleVariable(BigDecimal m, BigDecimal n)
      +evaluatePiecewise(...)
    }
    class SingleVariableExpression {
      +BigDecimal evaluate(BigDecimal m, BigDecimal n)
    }
    class PiecewiseFunction {
      +BigDecimal evaluate(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x)
    }
    class GraphFunctionCalculator {
      +BigDecimal calculate(BigDecimal x, BigDecimal R)
    }
    class PointRegionChecker {
      <<interface>>
      +boolean isInside(double x, double y, double R)
      +static PointRegionChecker create()
    }
    class PointRegionCheckerImpl {
      -static double TOLERANCE
      +boolean isInside(double x, double y, double R)
      -boolean checkQuadrantII(...)
      -boolean checkQuadrantIV(...)
      -void validateRadius(double R)
      -boolean isFinite(double value)
    }
    class Lb3ExpressionResult {
      +BigDecimal value()
    }
    Main --> SevenTeen
    Main --> PointRegionChecker
    SevenTeen --> SingleVariableExpression
    SevenTeen --> PiecewiseFunction
    SevenTeen --> GraphFunctionCalculator
    SevenTeen --> Lb3ExpressionResult
    PointRegionChecker <|.. PointRegionCheckerImpl : implements
```

## How the project is implemented

* `Main.java` reads all required input values for all four tasks
* each task has its own dedicated logic module
* clear labels in the output: `Task 1`, `Task 2`, `Task 3`, `Task 4`
* branching is implemented using the full `if / else if / else` structure
* Task 4 uses the contract pattern (interface + package-private implementation)

This is Laboratory Work 3, Variant 17, implemented in a structured OOP style with clear responsibility separation between classes.
