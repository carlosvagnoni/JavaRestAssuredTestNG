# Automated API Testing with Java, REST Assured, and TestNG 🤖☕

This project provides a structure and tools for automated API testing using Java, REST Assured, and TestNG, following Data-Driven Testing (DDT) best practices and employing the Service Object Model design pattern.

## Testing Swagger Petstore User Management Endpoints 🧪

This suite of tests is focused on validating and testing CRUD (Create, Read, Update, Delete) operations related to user management within the Swagger Petstore API (Base URL: https://petstore.swagger.io). Specifically, the tests interact with the following endpoints:

- Create User (POST): Endpoint URL: https://petstore.swagger.io/v2/user
- Get User (GET): Endpoint URL: https://petstore.swagger.io/v2/user/{username}
- Update User (PUT): Endpoint URL: https://petstore.swagger.io/v2/user/{username}
- Delete User (DELETE): Endpoint URL: https://petstore.swagger.io/v2/user/{username}

These tests are designed to ensure the functionality and correctness of these user management endpoints provided by the Swagger Petstore API. They interact with the defined URLs in the Routes class to perform comprehensive testing of user-related functionalities.

## Table of Contents 📑
- [Requirements](#requirements)
- [Folder Structure](#folder-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Test Execution](#test-execution)
- [Contact](#contact)

## <a id="requirements">Requirements 📋</a>

- JDK 21
- Lombok 1.18.30
- REST Assured 5.3.2
- TestNG 7.8.0

## <a id="folder-structure">Folder Structure 📂</a>

- **pom.xml:** Maven configuration file specifying project dependencies.
- **run.bat:** Batch script for execution in a Windows environment.

### Directory "src/test/java/api"

#### Directory "endpoints"

- **Routes.java:** Defines URLs for CRUD operations related to user management.
- **UserEndPoints.java:** Encapsulates HTTP requests for user-related API endpoints.

#### Directory "payload"

- **User.java:** Class representing user data for API operations.

#### Directory "test"

- **DDTests.java:** Test class implementing Data-Driven Testing (DDT) methodologies.
- **testng.xml:** TestNG configuration file for test execution.
- **UserTests.java:** Contains test methods to perform CRUD operations on user endpoints using generated/fake data.

#### Directory "utilities"

- **DataProviders.java:** Utility class providing data for tests.
- **ExtentReportManager.java:** Manages Extent reports for test execution.
- **XLUtility.java:** Utility class for handling Excel file operations.

### Directory "resources"

- **log4j2.xml:** Configuration file for Log4j2 for logging purposes.

### Directory "testData"

- **Userdata.xlsx:** Excel file containing user data used in testing.

## <a id="installation">Installation 🛠️</a>

1. Clone this repository:

    ```bash
    git clone https://github.com/carlosvagnoni/JavaRestAssuredTestNG.git
    cd JavaRestAssuredTestNG
    ```

2. Compile the project:

    ```bash
    mvn clean compile
    ```

## <a id="configuration">Configuration ⚙️</a>

- Userdata.xlsx can be modified to add any necessary data. The file accepts an arbitrary amount of data, accommodating any quantity of entries.

## <a id="test-execution">Test Execution ▶️</a>

Run all the tests:

```bash
mvn test
```

Open report:

```bash
start "" "reports\Test-Report.html"
```

**NOTE:**

- Set up the respective environment variables beforehand.
- On Windows environments, you can directly execute the `run.bat` file.

## <a id="contact">Contact 📧</a>

If you have any questions or suggestions, feel free to contact me through my social media accounts.

Thank you for your interest in this project!
