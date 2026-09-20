# Contributing

Use JDK 21 or newer and the Maven Wrapper. Run commands from the repository root.

```sh
./mvnw -B -ntp clean verify -Dtest=DriverSmokeTest -Dsurefire.failIfNoSpecifiedTests=false
./mvnw -B -ntp -pl :demowebshop-e2e-tests -am test
```

The first command compiles all production and test sources and runs the isolated browser smoke tests. Run the affected live demo module separately and include its report and any environment failures in your pull request.

- Give each scenario its own data; do not depend on someone else's demo account.
- Use explicit waits for asynchronous UI changes and always close the browser in framework teardown.
- Keep generated reports, browser profiles and IDE files out of commits.
- Report what was actually verified; a CAPTCHA rejection is not a successful purchase.
- Never bypass a demo site's access controls or CAPTCHA. Use an authorized test environment if the public demo is unavailable.

Public demo changes and outages can fail E2E runs independently of the build. Include the target URL, command, Java/browser version and relevant failure when opening an issue.
