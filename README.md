KSU SWE 3643 Software Testing and Quality Assurance Semester Project: Web-Based Calculator
Introduction
This project is a web-based calculator that supports various mathematical operations such as Mean, Sample Standard Deviation, Population Standard Deviation, Linear Regression, Z-Score, and more. The project also includes unit tests, end-to-end testing using Playwright, and documentation for setting up the environment and executing the application.

Table of Contents

1)Environment
2)Executing the Web Application
3)Executing Unit Tests
4)Reviewing Unit Test Coverage
5)Executing End-To-End Tests
6)Final Video Presentation

Team Members

Bill Roan Simo Masso
Architecture

<img width="510" alt="Screenshot 2024-12-02 at 10 26 19 PM" src="https://github.com/user-attachments/assets/7b85e5c7-f3bd-4834-b708-e8dd27868710">


The architecture of this web-based calculator is designed to handle different operations using modular components. Below is a diagram showing the architecture of the application:

Description of Components:
Calculator Logic Module: Contains classes for performing the core operations like Descriptive Statistics and Linear Regression.
Calculator Web Server App: Contains Models, Views, and Controllers responsible for interacting with the front-end.
Unit Tests: Logic unit tests to verify the functionality of each operation in the calculator logic module.
End-to-End Tests: Tests using Playwright to simulate user interactions with the web application.

Environment

To run the web application, unit tests, and end-to-end tests, follow these steps to set up the environment:

Install Java:
Download and install the latest Java version for your system. You can download it from the official website: Java Download.
Set up Playwright for End-to-End Testing:
Follow the Playwright installation guide for your platform. Playwright should be set up using the following command:
npm install playwright
Install Dependencies:
Clone the repository:
git clone https://github.com/your-repository-url.git
Navigate to the project folder and install dependencies:
cd your-project-folder
mvn install
Run the Web Application:
After setting up the environment, you can run the application on your local machine.
Executing the Web Application

Follow these steps to build and run the web application from the command line:

Build the application:
mvn clean install
Run the application:
mvn spring-boot:run
The application should start running on http://localhost:8080.
Access the application:
Open a web browser and navigate to the application:
http://localhost:8080
You should now be able to use the web-based calculator.
Executing Unit Tests

To execute all unit tests from the command line:

Run the Unit Tests:
mvn test
Sample output:
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building Calculator Project
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.3 s
[INFO] Finished at: 2024-12-02T19:35:10
[INFO] ------------------------------------------------------------------------
All unit tests should pass successfully, verifying the functionality of your calculator's logic.
Reviewing Unit Test Coverage

The unit tests cover 100% of the statements and paths in the Calculator Logic module. Below is a screenshot showing the test coverage report from JetBrains IntelliJ IDEA.

<img width="510" alt="Screenshot 2024-12-02 at 10 26 19 PM" src="https://github.com/user-attachments/assets/ffe2541f-16bc-4328-8889-c7b1104d7ffc">

Unfortunately, i wasn't able to achieve 100% coverage.

All operations such as Mean, Sample Standard Deviation, Population Standard Deviation, Linear Regression, and Z-Score calculations are fully tested.

Executing End-To-End Tests

To execute the end-to-end tests using Playwright:

Install Playwright:
If you haven't already, install Playwright by running:
npm install playwright
Run End-To-End Tests:
Navigate to the end-to-end test directory:
cd src/test/java/org/example/calculator/e2e
Run the Playwright tests:
mvn test

Final Video Presentation

The final video presentation should include a walk-through of the following:

Overview of the Calculator Web Application: Demonstrating the functionality of the calculator and all its operations.
Testing: A review of the unit tests and end-to-end tests, showing how they are executed and the results.
Architecture and Design: Explaining the overall structure of the project.
The video presentation should be uploaded to YouTube or another video-sharing platform and linked here.
