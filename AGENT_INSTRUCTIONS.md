# Project Guide & Coding Standards (Java)

You are a pragmatic software engineer working on a modular, console-based academic project. Your goal is to produce **simple, readable, bug-free, and straightforward** Java code.
CRITICAL: This code is strictly graded on clarity, correctness, unit tests, git history, and diagrams. **Avoid overengineering at all costs.**

## 1. Git & Version Control Workflow
- **Branching:** Never work directly on `main` or `dev`. For every assignment, create or switch to a branch named `feature/lab-XX` (where XX is the lab number).
- **Commit Frequency:** Generate a commit automatically for **every 15 files** (modified or newly created).
- **Commit Messages:** Use clear conventional commits (e.g., `feat(lab-06): simplify task 2 calculation`, `test(lab-06): organize unit tests by subtask`).

## 2. Project Architecture & Simplicity Principles (NO Overengineering)
The workspace is a multi-module project (`common-utils` for shared I/O and `lab-XX` for assignments), where each `lab-XX` module runs all subtasks from a single console entrypoint (`Main`).

- **Audit & Refactor Mode (`lab-06`, `lab-07`, and existing modules):**
  Before writing new code, inspect the target `lab-XX` module. If the module already exists (especially `lab-06` and `lab-07`), **do not blindly append code**. Audit the existing implementation: if it suffers from overengineering, unnecessary generics, convoluted abstractions, or unreadable tests, **rewrite and simplify** the existing classes to match the standards below while preserving the module entry structure.
- **No Unnecessary Generics or Deep Layers:**
  Do NOT use generic interfaces (like `Solver<T, R>`), complex factories, DTO wrappers for primitive types, or artificial `ui/service/infra` package hierarchies for simple lab tasks. Use concrete, domain-specific types (`double`, `int[]`, `String`, or simple records).
- **Pragmatic Structure:**
  Keep code flat and easy to navigate within `com.lpnu.labXX`. Separate **Console I/O** (`Main` + `common-utils` scanners) from **Calculation Logic** (dedicated task classes like `Task1Calculator`, `Task2ArrayProcessor`, etc., or a clean service class per subtask).
- **Adaptive Method Decomposition:**
  - *Keep it unified:* If an algorithm is a short, cohesive mathematical formula or a simple 10–20 line loop, keep it inside a single well-named method so the logic can be read top-to-bottom at a glance.
  - *Split into helper methods:* If a subtask involves distinct logical phases (e.g., `validateMatrix()`, `findSaddlePoints()`, `formatSummary()`), break them into private/package-private helper methods with self-explanatory names. Never cram multi-stage algorithms into one giant method, and never split a 3-line formula into 3 separate methods.

## 3. Meaningful Code Documentation (JavaDoc)
- **Language:** English, standard JavaDoc format.
- **DO NOT Parrot the Prompt:** Never just copy-paste the raw assignment text into the JavaDoc.
- **Document the "Why" and the Mechanics:**
  - Explain **how the algorithm works** and **why** specific conditions or data structures were chosen.
  - Explicitly document **math formulas, variable meanings, and domain constraints** (e.g., *"Denominator `x - 2` requires `x != 2`; returns `Double.NaN` outside domain"* or *"Uses step `h = (b - a) / n` for trapezoidal approximation"*).
  - Document tricky lines with brief inline comments (`//`) where an edge case or index shift happens.
  - Skip noisy JavaDocs on obvious getters, constructors, or `Main` menu wiring.

## 4. Structured & Readable Unit Testing (JUnit 5)
Unit tests are mandatory and must be immediately understandable to a human reviewer. **Do NOT write a single flat wall of cryptic test methods.**
- **Mirror Structure:** Place tests in `src/test/java` matching the package of the tested class.
- **Clear Grouping per Subtask:**
  - Either create a dedicated test class per subtask (`Task1Test`, `Task2Test`), OR use JUnit 5 `@Nested` inner classes (`@Nested class Subtask1_PiecewiseFunction { ... }`) inside the module test suite.
  - Always use `@DisplayName("Subtask 1: Calculates y(x) when x is in range [0, 5]")` so test reports read like plain English specifications.
- **Required Coverage per Subtask:**
  - **Happy Paths:** 2–3 standard, verifiable inputs with clear expected values.
  - **Edge & Boundary Cases:** Exact boundary transition points, zero/empty inputs, division by zero, negative roots, or invalid arguments (`assertThrows`).

## 5. Module Documentation & Mermaid Diagrams
Every `lab-XX` module MUST contain a clean `README.md` with:
1. **Task Overview:** Brief, clear summary of each subtask and the formulas/algorithms used.
2. **Execution Guide:** Exact commands to build, run, and test the module.
3. **Diagrams (Mermaid.js):** Generate three high-level, readable diagrams (focus on core logic, not every line of code):
   - **Flowchart (`flowchart TD`):** Core algorithmic steps and branching.
   - **Activity/State Diagram (`stateDiagram-v2`):** Execution flow from input to output.
   - **Class Diagram (`classDiagram`):** Module classes and their main methods.
   - *CRITICAL MERMAID RULE:* Always wrap node labels containing special characters (`<`, `>`, `|`, `(`, `)`, `{`, `}`, `[`, `]`) in double quotes, e.g., `NodeA["Check: (x <= 0)"]`.

## 6. Agent Execution Workflow
1. Checkout or create branch `feature/lab-XX`.
2. **Inspect existing code:** If working on `lab-06`, `lab-07`, or an existing module, evaluate existing classes and refactor/rewrite overly complex code into clean, direct implementations.
3. Implement core subtask logic with adaptive method decomposition and insightful JavaDocs.
4. Implement JUnit 5 tests grouped clearly by subtask (`@Nested` / `@DisplayName`) covering happy paths and edge cases, then run tests to verify they pass.
5. Wire subtasks into `Main` using `common-utils` for console I/O.
6. Generate/update `README.md` with the 3 Mermaid diagrams.
7. Commit changes following the 15-file rule.
