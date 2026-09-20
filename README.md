# Selenium Automation Suite

Java UI automation examples using **Selenium 4.49**, **JUnit 5**, **TestNG** and **Cucumber** in one Maven reactor.

[![Build and browser smoke](https://github.com/cihat-kose/selenium-automation-suite/actions/workflows/ci.yml/badge.svg)](https://github.com/cihat-kose/selenium-automation-suite/actions/workflows/ci.yml)
[![Java 21](https://img.shields.io/badge/Java-21-orange)](https://adoptium.net/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Quick start

Install **JDK 21 or newer** and **Google Chrome**. Maven is provided by the wrapper; a separate Maven installation is unnecessary. The first run needs internet access to download Maven, dependencies and the browser driver.

```sh
git clone https://github.com/cihat-kose/selenium-automation-suite.git
cd selenium-automation-suite
```

Compile every module and run the isolated browser smoke tests:

```powershell
# Windows PowerShell
.\mvnw.cmd -B -ntp clean verify "-Dtest=DriverSmokeTest" "-Dsurefire.failIfNoSpecifiedTests=false"
```

```sh
# Linux / macOS
bash mvnw -B -ntp clean verify -Dtest=DriverSmokeTest -Dsurefire.failIfNoSpecifiedTests=false
```

This opens a headless browser, submits a local HTML form and verifies the asynchronous result. It does **not** claim that public demo E2E tests passed. The command compiles every suite and runs the two tests in `common`; modules without this test class are intentionally not selected.

## Suites

| Module | Framework | Coverage |
| --- | --- | --- |
| `common` | JUnit 5 | Shared browser factory and isolated browser smoke |
| `demowebshop-e2e-tests` | JUnit 5 | Registration, duplicate email, login, rejected credentials, cart |
| `demowebshop-e2e-tests-testng` | TestNG | Registration, login and checkout workflow |
| `parabank-cucumber-tests` | Cucumber + TestNG | Registration, login, rejected credentials and bill payment |
| `shopdemo-e2e-tests` | JUnit 5 | Contact validation, promo/payment errors and CAPTCHA rejection |
| `nopcommerce-admin-testng` | TestNG | Admin login, navigation and customer creation |
| `itera-tests` | JUnit 5 | Registration, login and customer creation with mixed locators |
| `itera-tests-xpath` | JUnit 5 | The same Itera exercises using XPath |

**Live demo availability is separate from build health.** On 2026-09-20, Itera returned `ERR_NAME_NOT_RESOLVED` and the NopCommerce admin demo presented a Cloudflare verification page in both Chrome and Edge. Its exercises remain available for a compatible restored environment using `-Ditera.url=...`. See [validation results](docs/VALIDATION.md) for the actual results and remaining blockers.

## Run live E2E tests

Run commands from the repository root. `-am` builds the shared module and parent projects too. Replace `bash mvnw` with `.\mvnw.cmd` on Windows.

```sh
# One complete suite
bash mvnw -B -ntp -pl :demowebshop-e2e-tests -am test

# All suites, continuing across independent module failures
bash mvnw -B -ntp -fae verify

# Cucumber smoke: registration and successful login
bash mvnw -B -ntp -pl :parabank-cucumber-tests -am test -PSmoke

# All Cucumber scenarios, executed once
bash mvnw -B -ntp -pl :parabank-cucumber-tests -am test -PRegression

# Show the browser, or choose another installed browser
bash mvnw -pl :common test -Dheadless=false
bash mvnw -pl :common test -Dbrowser=firefox
```

The default `test`/`verify` command runs the live suites as well as smoke tests. Unavailable sites cause failures, not silent skips. Public demos may reset data, change their UI, enforce CAPTCHA or be offline. Use dedicated authorized test environments for dependable E2E pipelines.

### Configuration

Pass options as Maven system properties (`-Dname=value`). Quote the whole option in PowerShell when it contains periods or special characters.

| Property | Default / purpose |
| --- | --- |
| `browser` | `chrome`; also supports `firefox` and `edge` |
| `headless` | `true`; set `false` for a visible browser |
| `demowebshop.url` | `https://demowebshop.tricentis.com/` |
| `parabank.url` | `https://parabank.parasoft.com/parabank/index.htm` |
| `shopdemo.url` | `https://shopdemo.e-junkie.com/` |
| `nopcommerce.url` | `https://admin-demo.nopcommerce.com/login?` |
| `itera.url` | `https://itera-qa.azurewebsites.net/` |
| `cucumber.filter.tags` | Select scenarios, e.g. `@smoke` |

Browser sessions close in framework teardown, including failed tests. JUnit tests use one session per test, TestNG workflows use one per class, and Cucumber uses one per scenario. Run the stateful TestNG checkout class as a complete suite; its dependencies are explicit. No parallel execution is enabled by default.

## Reports and GitHub Actions

- Every module writes XML/text reports to `target/surefire-reports/`.
- ParaBank additionally produces `target/cucumber-report.html` and `target/cucumber.json`, with screenshots attached on scenario failure.
- **Build and browser smoke** runs on pushes and pull requests: all-module compilation plus isolated browser smoke.
- **Live demo E2E** is manually dispatched from the Actions tab with a selected module. It reports real failures and uploads results even when the run fails.
- Dependabot checks Maven dependencies and Actions monthly.

Generated reports stay out of Git. The ShopDemo CAPTCHA test verifies rejection; it does not place or claim a successful order.

## Repository layout

```text
common/                Shared Selenium driver factory and browser smoke
junit-projects/        Four JUnit suites
testng-projects/       Two TestNG suites
cucumber-projects/     ParaBank features, steps and runners
.github/workflows/     Build/smoke and optional live E2E workflows
docs/                  Validation evidence and known limitations
```

Contributions: [CONTRIBUTING.md](CONTRIBUTING.md). License: [MIT](LICENSE).

Implementation references: [Maven Wrapper](https://maven.apache.org/tools/wrapper/), [Selenium browser options](https://www.selenium.dev/documentation/webdriver/drivers/options/).
