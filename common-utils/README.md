# common-utils (Shared utilities)

This module is a placeholder for centralized, reusable utilities such as safe terminal scanners, generic math helpers, and robust input retry logic. It is intentionally lightweight — add carefully-reviewed utilities here and keep business logic inside lab modules.

Usage:
- Add common helpers in `src/main/java/com/lpnu/common/*`.
- Publish as a normal Maven module; other lab modules can declare a dependency on `com.lpnu:common-utils`.

This module was created to align the repository with the required multi-module layout in AGENT_INSTRUCTIONS.md.
