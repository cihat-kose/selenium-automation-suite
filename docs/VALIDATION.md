# Validation record

Validation date: **2026-09-20**. Live browser checks were performed on Windows 11 using Chrome 153 and Edge 153. Maven Wrapper: 3.9.11. Selenium: 4.49.0. Compilation targets Java 21.

## Verified suites

| Suite | Result | Environment |
| --- | --- | --- |
| Shared browser smoke | 2 passed | Chrome / Edge; local HTML, no public demo dependency |
| DemoWebShop / JUnit | 5 passed | Chrome, JDK 24 with release 21 |
| DemoWebShop / TestNG | 5 passed, including checkout | Chrome, JDK 24 with release 21 |
| ShopDemo / JUnit | 5 passed | Chrome, JDK 24 with release 21 |
| ParaBank smoke | 2 passed | Chrome, JDK 24 with release 21 |

These are results from targeted module runs, not a claim that the full live reactor passes. The first full run exposed stale-element failures in both DemoWebShop suites; both were fixed and their complete suites rerun successfully. ShopDemo was rerun after the Selenium upgrade and all five tests passed.

## External blockers

- **Itera (both modules):** the public hostname returned `net::ERR_NAME_NOT_RESOLVED` in all six scenarios. Supply a compatible restored deployment via `-Ditera.url=...`. Updated account isolation and teardown compile, but cannot be validated end to end against the unavailable service.
- **NopCommerce admin:** the public site returned a Cloudflare verification page instead of the login form in both Chrome and Edge. The screenshot and HTML confirmed the challenge. Login could not complete, so navigation and customer creation remain unverified. Use an authorized environment via `-Dnopcommerce.url=...`; no challenge bypass is implemented.
- **Local Chrome startup:** several runs intermittently failed before navigation with `SessionNotCreatedException: Chrome instance exited`. Successful Chrome suite runs are listed above; Edge was used to distinguish site failures from this local browser issue. No automatic retry hides these failures.

The live tests keep failing on unavailable targets; they are not silently disabled. GitHub's build/smoke job compiles every suite and tests the local browser path. The separate manually dispatched E2E workflow exercises the selected public demo and preserves failure reports.

## Commands

```powershell
.\mvnw.cmd -B -ntp -pl :demowebshop-e2e-tests,:demowebshop-e2e-tests-testng -am verify
.\mvnw.cmd -B -ntp -pl :shopdemo-e2e-tests,:parabank-cucumber-tests -am verify -PSmoke
.\mvnw.cmd -B -ntp -pl :parabank-cucumber-tests -am verify -PRegression "-Dbrowser=edge"
.\mvnw.cmd -B -ntp clean verify "-Dtest=DriverSmokeTest" "-Dsurefire.failIfNoSpecifiedTests=false"
```

Reports are generated under each module's `target/surefire-reports/`. Cucumber also generates HTML/JSON with failure attachments; admin failures produce PNG/HTML under `target/screenshots/`. Generated reports are intentionally not committed.

