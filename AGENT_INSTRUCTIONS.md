# Project Guide & Coding Standards

You are an expert software engineer generating code for a modular, console-based academic project. Your goal is to produce clean, bug-free, and highly maintainable code.
CRITICAL: This code is strictly graded. Severe bugs, missing tests, missing git commits, or missing diagrams result in heavy penalties.

## 1. Git & Version Control Workflow
You must strictly follow this Git workflow to ensure a clean repository history:
*   **Branching:** Never work directly on `main` or `dev`. For every new assignment, create and switch to a new branch named `feature/lab-XX` (where XX is the lab number).
*   **Commit Frequency:** You must generate a commit automatically for **every 15 files** (modified or newly created). Do not wait until the entire task is finished if the file count exceeds 15.
*   **Commit Messages:** Use clear, conventional commit messages (e.g., `feat(lab-03): implement piecewise function logic`, `test(lab-03): add unit tests for edge cases`).

## 2. Project Architecture & Modularity
The workspace is a multi-module project.
*   **`common-utils` (Shared Root):** Contains centralized, reusable utilities (safe terminal scanners, generic math helpers, input retry logic). Lab modules depend on this to avoid duplicating boilerplate.
*   **`lab-XX` (Task Modules):** Each assignment lives in its own isolated module.
*   **Contract Pattern:** Define core behavior using interfaces (e.g., `EquationSolver`). Hide the implementation in package-private classes. The `Main` executable should only interact with the contract. Keep I/O logic strictly separate from Math/Business logic.

## 3. Code Documentation (JavaDoc)
*   **Language:** All code documentation must be written in English using standard JavaDoc format.
*   **Conciseness vs. Detail:** Keep JavaDocs concise for standard boilerplate or utility methods.
*   **Core Logic Detail:** For methods implementing the core mathematical or business logic of the assignment, the JavaDoc MUST be highly detailed and accurate. It should explain the exact context, the mathematical formulas being applied, and the specific assignment constraints, so a developer can understand the original task just by reading the comments.

## 4. Mandatory Unit Testing
Unit tests are strictly required. Missing tests will result in project failure.
*   **Hierarchy:** Test classes must mirror the exact package structure of `src/main/java` inside `src/test/java`.
*   **Coverage:** You must implement and execute tests for:
    *   **Happy Paths:** Several successful, typical executions.
    *   **Edge/Problematic Cases:** Division by zero, negative roots, null inputs, boundary values (e.g., testing the exact transition points in piecewise functions).
*   Use standard testing frameworks (e.g., JUnit 5).

## 5. Module Documentation & Mermaid Diagrams
EVERY task module MUST contain a highly polished `README.md` file.
Diagrams must be **concise and high-level**. Do not map every single line of code; map the core logical steps and branching.

The README must include:
1.  **Task Description:** A clear explanation of the mathematical problem.
2.  **Execution Guide:** How to compile and run the module (mentioning `make` targets if applicable).
3.  **Diagrams (Mermaid.js):** You must generate exactly three types of visual diagrams using Mermaid inside markdown blocks:
    *   **Flowchart (`flowchart TD`):** Showing the algorithmic execution and branching logic.
    *   **UML Activity Diagram (`stateDiagram-v2` or `activity`):** Illustrating the execution flow and state changes.
    *   **Structural Diagram (`classDiagram`):** Showing the architecture, interfaces, and classes of the module.
    *   *CRITICAL MERMAID RULE:* Always wrap nodes containing special characters (like `<`, `>`, `|`, `(`, `)`, `{`, `}`) in double quotes to prevent rendering errors (e.g., `NodeA["Condition: (x < 0)"]`).

## 6. Agent Execution Workflow
When asked to implement a new task, execute these steps in order:
1. Initialize the Git branch (`feature/lab-XX`).
2. Write the core logic (Contracts and Implementations) with detailed JavaDoc.
3. Write the Unit Tests (Happy paths and Edge cases).
4. Create the `Main` execution script using `common-utils` for I/O.
5. Generate the `README.md` with the 3 required Mermaid diagrams.
6. Commit the files following the "every 15 files" rule.