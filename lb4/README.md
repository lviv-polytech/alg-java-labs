# Lab 4: Trigonometric Series Sum Calculation

## 1. Task Description
The goal of this module is to compute the finite sum of a trigonometric expression using four different looping constructions available in Java (`while`, `do-while`, `for` incrementing, `for` decrementing). The mathematical expression is:

$$ S = \sum_{i=1}^{N} \frac{\sin i}{1+\cos i} $$

The program queries the user for a single integer input `N` (upper limit of summation where `N >= 1`), computes the sum using all four looping strategies, and prints exactly four lines with the results. All four outputs must match precisely, thereby proving algorithmic equivalence of the loop constructs.

## 2. Execution Guide
To run this laboratory job from the root directory:
```bash
# Compile tests and build the main artifact
mvn clean package

# Or execute only the specific module test
mvn test -pl lb4

# Run the user interface
java -cp lb4/target/classes:common-utils/target/classes com.lpnu.lb4.ui.Main
```

## 3. Diagrams

### Flowchart: Algorithmic Logic
```mermaid
flowchart TD
    A["Start"] --> B["Prompt User for N"]
    B --> C["Read input string"]
    C --> D{"Is N >= 1?"}
    D -- "No" --> E["Show Error, Retry"]
    E --> B
    D -- "Yes" --> F["Initialize Calculators List"]
    F --> G["Loop over Calculators"]
    G --> H{"Has Next Calculator?"}
    H -- "Yes" --> I["calc.calculate(N)"]
    I --> J["Print result for current calc"]
    J --> G
    H -- "No" --> K["End"]
```

### UML Activity Diagram: Execution Flow
```mermaid
stateDiagram-v2
    [*] --> InputPhase
    InputPhase --> ValidationPhase: "Enter N (integer >= 1)"
    ValidationPhase --> InputPhase: "Invalid Input"
    ValidationPhase --> CalculationPhase: "Valid N"
    
    state CalculationPhase {
        [*] --> ExecWhile
        ExecWhile --> ExecDoWhile
        ExecDoWhile --> ExecForInc
        ExecForInc --> ExecForDec
        ExecForDec --> [*]
    }
    
    CalculationPhase --> ResultOutputPhase
    ResultOutputPhase --> [*]: "Terminate"
```

### Structural Class Diagram: Architecture
```mermaid
classDiagram
    class Main {
        +main(args: String[]) void
    }
    class SeriesSumCalculator {
        <<interface>>
        +calculate(n: int) double
        +getLoopType() String
    }
    
    class WhileSeriesSumCalculator {
        +calculate(n: int) double
        +getLoopType() String
    }
    class DoWhileSeriesSumCalculator {
        +calculate(n: int) double
        +getLoopType() String
    }
    class ForIncrementSeriesSumCalculator {
        +calculate(n: int) double
        +getLoopType() String
    }
    class ForDecrementSeriesSumCalculator {
        +calculate(n: int) double
        +getLoopType() String
    }
    
    class CalculatorFactory {
        <<factory>>
        +getAllCalculators() List~SeriesSumCalculator~
    }
    
    SeriesSumCalculator <|.. WhileSeriesSumCalculator
    SeriesSumCalculator <|.. DoWhileSeriesSumCalculator
    SeriesSumCalculator <|.. ForIncrementSeriesSumCalculator
    SeriesSumCalculator <|.. ForDecrementSeriesSumCalculator
    
    CalculatorFactory --> SeriesSumCalculator : creates
    Main --> CalculatorFactory : invokes
    Main --> SeriesSumCalculator : uses
```
