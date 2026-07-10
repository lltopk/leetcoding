# AGENTS.md

## Build

- Java 17, Maven (no parent POM). Build each module individually:
  ```powershell
  mvn -f classify compile
  mvn -f leetcode-100 compile
  ```
- 8 independent modules: `classify/`, `leetcode-{easy,medium,hard}/`, `leetcode-100/`, `vsc_coding/`, `company-{hsbc,ydtx}/`
- No cross-module dependencies — each POM is standalone.
- Some modules ship a Maven wrapper (`.mvn/`, `mvnw.cmd`), others don't. Use `mvn` directly from any module dir.

## Tests

- Only `classify`, `leetcode-100`, `leetcode-medium` list JUnit 3.8.1 as a dependency.
- Test running: `mvn -f classify test`
- Most modules have zero or minimal test coverage.

## Structure

- Data structure classes (`ListNode`, `TreeNode`, `DListNode`) are **duplicated per module** — changes to one won't propagate.
- Different groupId schemes in use: `org.lyflexi`, `com.hm`, `com.lyflexi`, `org.vsc.lltopk` — do not assume a single namespace.
- `classify/` is the largest module, organized by algorithmic strategy subdirectories (e.g., `strategy_binary/`, `strategy_retrieval_dfs/`).

## Constraints

- No CI/CD, no linter, no formatter config.
- No `opencode.json` or other instruction files exist — this file is the only agent guidance.
- `.gitignore` at root covers `target/`, `*.iml`, `.idea`, `.vscode/`.
