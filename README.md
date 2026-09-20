# Algorithms and Data Structures (Academic Lab Projects)

This repository contains an academic portfolio covering fundamental computer science algorithms, mathematical simulations, and memory structure interactions. It is built as a multi-module Maven Java project simulating standard algorithmic challenges (often solved in C/C++) through explicit architectural paradigms in Java.

## Repository Overview

The project is structured entirely around modularity, separation of concerns (Contract Pattern), and academic evaluation mappings.

### Module Structure
* **`common-utils/`**: Shared capabilities, Math utilities, safe asynchronous Input/Output scanners, and cross-cutting components used by all laboratory workloads.
* **`lb1/` - `lb3/`**: Early algorithmic works encompassing fundamental logic routines.
* **`lb4/` (Loops & Conditionals)**: Complex branching logic, Taylor series via recurrent cyclic formulations, geometric area targeting, piecewise functionalities, and multi-nested visual loops.
* **`lb5/` (Recursion Mastery)**: Transforming standard Iterative sequences into Recursive descent (tail recursion) and Recursion ascent paradigms. Tracks dynamic physical stack limitations and Call-Stack evaluations. 
* **`lb6/` (1D Dynamic Memory)**: Handling contiguous logic in single-layer domains: mapping stable selections, custom index filtering, conditionally tracking extremes, and Generic Template (`<T>`) console I/O equivalents to C++ templates.
* **`lb7/` (2D Multi-dimensional Topology)**: Matrix permutations without flattening: executing multi-key row swaps (stable database grouping), localized extreme swapping across math pair bindings, dynamic sub-array nullification mapping `new`/`delete` bounds, and specialized geometric bounds modification.
* **`reports/` (`.local/reports`)**: Every task module has localized `MDX` academic evaluations. These reports explain Flowchart execution pathways and theoretical state-machine transitions rendering natively supported Mermaid architectures. Each covers constraints, implementation theory, and algorithmic equivalence outcomes.

## Technical Standards

* **Language**: Java SDK 17+ (Uses Records for struct modeling and pass-by-reference simulation).
* **Architecture Rules**: Logic blocks are completely separated from input/output modules (`ui/` vs `service/`). No `System.out` inside Math boundaries. Strictly modular (no global states or variable bleeding).
* **Testing**: Comprehensive JUnit integrations guaranteeing outputs match $100\%$ evenly between Recursive variants and Iterative counterparts down to precision deltas (`1e-9`).

## Using the Workspace

The repository provides a unified `Makefile` for streamlined interactions, compilation, testing, and lifecycle management without having to memorize manual Maven commands.

### Running Applications

To launch the menu-driven runner for a specific laboratory, you can use the predefined Makefile references:

```bash
# Easiest way to run individual labs visually
make run-lb4
make run-lb5
make run-lb6
make run-lb7
```

*Or via parameterized execution:*
```bash
make run LAB=lb7
```

*(Each laboratory contains its own sub-menu where you select individual task executions).*

### Executing Tests

Each module operates isolated CI definitions guaranteeing zero interference:

```bash
# Run unit tests across all projects globally
make test

# Drill-down exact academic suites
make test-lb5
make test-lb7
```