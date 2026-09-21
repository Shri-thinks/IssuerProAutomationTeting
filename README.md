# IssuerPro Automation Framework

Enterprise test automation framework built for **IssuerPro Card Issuer Processing Console** (`https://issuer-platform.emergent.host/login`).

Combines **Selenium WebDriver (Java 17)** following the **Page Object Model (POM)** pattern for UI regression testing with **RestAssured** for comprehensive API verification.

---

## Architecture Overview

```
src
├── main/java/com/issuerpro
│   ├── config
│   │   └── ConfigManager.java          # Thread-safe config loader with system property overrides
│   ├── driver
│   │   ├── DriverManager.java          # ThreadLocal<WebDriver> for parallel safety
│   │   └── DriverFactory.java          # Headless Chrome / Firefox / Edge driver builder
│   ├── pages
│   │   ├── BasePage.java               # Explicit wait utilities and core Selenium actions
│   │   ├── LoginPage.java              # Page Object for /login
│   │   ├── DashboardPage.java          # Page Object for Processor Command dashboard (/)
│   │   ├── CustomersPage.java          # Page Object for /customers directory
│   │   └── TestLabPage.java            # Page Object for /test-lab reset & fraud playground
│   ├── api
│   │   ├── ApiEndpoints.java           # Centralized API route definitions
│   │   ├── ApiSpecFactory.java         # RestAssured request/response specification builders
│   │   ├── models                      # Jackson DTOs (LoginRequest, CustomerRequest, etc.)
│   │   └── clients                     # High-level API clients (Auth, Customer, Transaction, Admin)
│   └── utils
│       └── ScreenshotUtil.java         # Automated screenshot capture on UI test failure
└── test
    ├── java/com/issuerpro
    │   ├── listeners
    │   │   └── TestListener.java       # TestNG listener for execution logging & failure screenshots
    │   ├── ui
    │   │   ├── BaseUiTest.java         # WebDriver lifecycle (@BeforeMethod, @AfterMethod)
    │   │   ├── LoginUiTest.java        # Authentication, demo role autofill, and error validation
    │   │   ├── DashboardUiTest.java    # Dashboard metrics, widgets, and navigation
    │   │   └── CustomersUiTest.java    # Customer table and search verification
    │   └── api
    │       ├── BaseApiTest.java        # RestAssured test base with admin auth cookies
    │       ├── ResetAndSeedApiTest.java# Test environment reset via POST /api/admin/reset
    │       ├── AuthApiTest.java        # Login, logout, and /auth/me session checks
    │       ├── CustomerApiTest.java    # GET /customers and POST /customers creation
    │       └── TransactionApiTest.java # Dashboard KPIs and authorization engine purchase simulation
    └── resources
        ├── config.properties           # Target URLs, headless browser flag, timeouts, credentials
        ├── logback-test.xml            # Clean logging configuration
        ├── testng-ui.xml               # TestNG UI test suite
        ├── testng-api.xml              # TestNG API test suite
        └── testng-all.xml              # Combined test suite
```

---

## Prerequisites

- **Java Development Kit**: JDK 17 or higher
- **Maven**: 3.9+
- **Google Chrome**: Latest version (Selenium Manager manages matching driver binaries automatically)

---

## Configuration (`config.properties`)

Edit `src/test/resources/config.properties` or override via `-D` flags:

```properties
app.url=https://issuer-platform.emergent.host
app.login.path=/login
api.base.url=https://issuer-platform.emergent.host/api

browser=chrome
browser.headless=true
explicit.wait.timeout=15

admin.username=admin
admin.password=Admin@123
```

To run tests with a visual browser (headed mode):
```bash
mvn test -Dbrowser.headless=false
```

---

## Running the Tests

### 1. Run Complete Test Suite (API + UI)
```bash
mvn clean test
```

### 2. Run API Tests Only
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng-api.xml
```

### 3. Run UI Tests Only
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng-ui.xml
```

### 4. Run a Specific Test Class
```bash
mvn test -Dtest=LoginUiTest
mvn test -Dtest=AuthApiTest
```

---

## Reports & Screenshots
- **Surefire HTML Reports**: `target/surefire-reports/index.html`
- **Failure Screenshots**: Automatically saved in `target/screenshots/` whenever a UI test fails.
