# Lab 5: Version Control and Unit Testing (Task 5.0)

## 1. Task Description
This laboratory module aims to demonstrate the fundamentals of version control using Git, cloud repository hosting via GitHub, and automated Unit Testing. The module specifically focuses on setting up isolated branches (`feature/lab-05`), writing a trivial testing method, and confirming functionality using test assertion libraries (`JUnit - Assertions.assertEquals()`).

## 2. Execution Guide
To run tests and confirm that the environment is fully functional:
```bash
# Compile tests and build the main artifact
mvn clean test -pl lb5
```

## 3. Diagrams

### Flowchart: Git Branching & Merging Flow
```mermaid
flowchart TD
    A["git checkout main"] --> B["git checkout -b feature/lab-05"]
    B --> C["Work on new assignment"]
    C --> D["git add ."]
    D --> E["git commit -m 'feat(lab-05)'"]
    E --> F["git push origin feature/lab-05"]
    F --> G["Create Pull Request on GitHub"]
    G --> H{"Code Review & Tests Past?"}
    H -- "Yes" --> I["Merge into main"]
    H -- "No" --> C
    I --> J["git checkout main && git pull"]
```

### UML Activity Diagram: File States in Git
```mermaid
stateDiagram-v2
    [*] --> Untracked: "New file created (e.g. MathService.java)"
    Untracked --> Staged: "git add"
    Modified --> Staged: "git add"
    Staged --> Unmodified: "git commit"
    Unmodified --> Modified: "File edited"
    Unmodified --> [*]: "File safely in local repository"
```

### Structural Class Diagram: Unit Testing Architecture
```mermaid
classDiagram
    class MathService {
        +sum(a: int, b: int) int
    }
    
    class MathServiceTest {
        +testSum() void
    }
    
    class Assertions {
        <<JUnit Core>>
        +assertEquals(expected, actual, message) void
    }

    MathServiceTest --> MathService : creates & tests
    MathServiceTest --> Assertions : verifies state with
```