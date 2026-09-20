# Lab 6: Arrays and Memory Structures

## 1. Task Description
This laboratory module demonstrates generation and manipulation of internal structures handling 1D data representation arrays.
Algorithms run array evaluation strictly adhering to dual-implementation directives modeling Iterative and Recursion mechanics.

**Task 6.1: 1D Array Iterative vs Recursive scans**
Identifies matrix members mapping against criteria `(r[i] % 6 == 0) || (i % 5 != 0)` counting and destroying matching records setting output bounds.
Demonstrates Java primitives array passing by reference vs deep value accumulations tracking algorithm counts.

## 2. Compilation and Execution
```bash
# Build the project
mvn clean install -pl lb6

# Run Main
java -cp common-utils\target\classes;lb6\target\classes com.lpnu.lb6.ui.Main
```
**Task 6.2: Max Even Element Index**
Determines extreme conditional members (maximum values among strictly even parity constraints) within scalar 1D sets, resolving empty-condition fallbacks (returning `-1` for arrays completely lacking even entities). Replicated across identical iterative bounds and recursive node-chain scans.

